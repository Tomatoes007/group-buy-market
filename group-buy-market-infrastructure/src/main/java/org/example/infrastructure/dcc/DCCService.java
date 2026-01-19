package org.example.infrastructure.dcc;

import org.example.types.annotations.DCCValue;
import org.springframework.stereotype.Service;

@Service
public class DCCService {

    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch;

    @DCCValue("cutRange:100")
    private String cutRange;

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

}
