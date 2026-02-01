package org.example.infrastructure.dcc;

import org.example.types.annotations.DCCValue;
import org.example.types.common.Constants;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DCCService {

    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch;

    @DCCValue("cutRange:100")
    private String cutRange;

    @DCCValue("scBlacklist:s02c02")
    private String scBlacklist;

    public boolean isDownGradeSwitch(){
        return "1".equals(this.downgradeSwitch);
    }
    public boolean isCutRange(String userId){
        int hashCode = Math.abs(userId.hashCode());
        int lastTwoDigits = hashCode % 100;
        if(lastTwoDigits<=Integer.parseInt(this.cutRange)){
            return true;
        }
        return false;
    }

    public boolean isSCBlackIntercept(String source, String channel) {
        List<String> list = Arrays.asList(scBlacklist.split(Constants.SPLIT));
        return list.contains(source + channel);
    }

}
