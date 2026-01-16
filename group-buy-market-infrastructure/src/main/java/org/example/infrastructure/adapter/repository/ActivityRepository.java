package org.example.infrastructure.adapter.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.adapter.repository.IActivityRepository;
import org.example.domain.activity.model.valobj.DiscountTypeEnum;
import org.example.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import org.example.domain.activity.model.valobj.SCSkuActivityVO;
import org.example.domain.activity.model.valobj.SkuVO;
import org.example.infrastructure.dao.IGroupBuyActivityDao;
import org.example.infrastructure.dao.IGroupBuyDiscountDao;
import org.example.infrastructure.dao.ISCSkuActivityDao;
import org.example.infrastructure.dao.ISkuDao;
import org.example.infrastructure.dao.po.GroupBuyActivity;
import org.example.infrastructure.dao.po.GroupBuyDiscount;
import org.example.infrastructure.dao.po.SCSkuActivity;
import org.example.infrastructure.dao.po.Sku;
import org.example.infrastructure.redis.IRedisService;
import org.redisson.api.RBitSet;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Slf4j
@Repository
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;
    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;
    @Resource
    private ISkuDao skuDao;
    @Resource
    private ISCSkuActivityDao scSkuActivityDao;
    @Resource
    private IRedisService redisService;

//    @Override
//    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel) {
//        GroupBuyActivity groupBuyActivityReq=new GroupBuyActivity();
//        groupBuyActivityReq.setSource(source);
//        groupBuyActivityReq.setChannel(channel);
//        GroupBuyActivity groupBuyActivityRes = groupBuyActivityDao.queryValidGroupBuyActivityId(groupBuyActivityReq);
//        String discountId = groupBuyActivityRes.getDiscountId();
//        GroupBuyDiscount groupBuyDiscountRes = groupBuyDiscountDao.queryGroupBuyDiscountById(discountId);
//        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount= GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
//                .discountName(groupBuyDiscountRes.getDiscountName())
//                .discountDesc(groupBuyDiscountRes.getDiscountDesc())
//                .discountType(DiscountTypeEnum.get(groupBuyDiscountRes.getDiscountType()))
//                .marketPlan(groupBuyDiscountRes.getMarketPlan())
//                .marketExpr(groupBuyDiscountRes.getMarketExpr())
//                .tagId(groupBuyDiscountRes.getTagId()).build();
//        return GroupBuyActivityDiscountVO.builder()
//                .activityId(groupBuyActivityRes.getActivityId())
//                .activityName(groupBuyActivityRes.getActivityName())
//                .source(groupBuyActivityRes.getSource())
//                .channel(groupBuyActivityRes.getChannel())
//                .goodsId(groupBuyActivityRes.getGoodsId())
//                .groupBuyDiscount(groupBuyDiscount)
//                .groupType(groupBuyActivityRes.getGroupType())
//                .takeLimitCount(groupBuyActivityRes.getTakeLimitCount())
//                .target(groupBuyActivityRes.getTarget())
//                .validTime(groupBuyActivityRes.getValidTime())
//                .status(groupBuyActivityRes.getStatus())
//                .startTime(groupBuyActivityRes.getStartTime())
//                .endTime(groupBuyActivityRes.getEndTime())
//                .tagId(groupBuyDiscountRes.getTagId())
//                .tagScope(groupBuyActivityRes.getTagScope()).build();
//    }

    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId) {
        // 根据SC渠道值查询配置中最新的1个有效的活动
        GroupBuyActivity groupBuyActivityRes = groupBuyActivityDao.queryValidGroupBuyActivityId(activityId);

        if(groupBuyActivityRes == null){
            log.error("不存在activityId = {} 的优惠活动，或者该优惠活动已经停止", activityId);
            return null;
        }
        //查询活动的折扣信息
        String discountId = groupBuyActivityRes.getDiscountId();
        GroupBuyDiscount groupBuyDiscountRes = groupBuyDiscountDao.queryGroupBuyDiscountById(discountId);
        if(groupBuyDiscountRes == null){
            return null;
        }
        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
                .discountName(groupBuyDiscountRes.getDiscountName())
                .discountDesc(groupBuyDiscountRes.getDiscountDesc())
                .discountType(DiscountTypeEnum.get(groupBuyDiscountRes.getDiscountType()))
                .marketPlan(groupBuyDiscountRes.getMarketPlan())
                .marketExpr(groupBuyDiscountRes.getMarketExpr())
                .tagId(groupBuyDiscountRes.getTagId())
                .build();

        return GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivityRes.getActivityId())
                .activityName(groupBuyActivityRes.getActivityName())
                .groupBuyDiscount(groupBuyDiscount)
                .groupType(groupBuyActivityRes.getGroupType())
                .takeLimitCount(groupBuyActivityRes.getTakeLimitCount())
                .target(groupBuyActivityRes.getTarget())
                .validTime(groupBuyActivityRes.getValidTime())
                .status(groupBuyActivityRes.getStatus())
                .startTime(groupBuyActivityRes.getStartTime())
                .endTime(groupBuyActivityRes.getEndTime())
                .tagId(groupBuyActivityRes.getTagId())
                .tagScope(groupBuyActivityRes.getTagScope())
                .build();
    }

    @Override
    public SkuVO querySkuByGoodsId(String goodsId) {
        Sku sku=skuDao.querySkuByGoodsId(goodsId);
        if(sku==null){
            return null;
        }
        return SkuVO.builder()
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice()).build();
    }

    @Override
    public SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId) {
        SCSkuActivity scSkuActivityReq = new SCSkuActivity();
        scSkuActivityReq.setSource(source);
        scSkuActivityReq.setChannel(channel);
        scSkuActivityReq.setGoodsId(goodsId);
        SCSkuActivity scSkuActivityRes = scSkuActivityDao.querySCSkuActivityByGoodsId(scSkuActivityReq);

        if (null == scSkuActivityRes) return null;
        return SCSkuActivityVO.builder()
                .source(scSkuActivityRes.getSource())
                .chanel(scSkuActivityRes.getChannel())
                .activityId(scSkuActivityRes.getActivityId())
                .goodsId(scSkuActivityRes.getGoodsId())
                .build();
    }

    @Override
    public boolean isTagCrowRange(String tagId, String userId) {
        RBitSet bitSet = redisService.getBitSet(tagId);
        if (!bitSet.isExists()) return true;
        return bitSet.get(redisService.getIndexFromUserId(userId));
    }

}
