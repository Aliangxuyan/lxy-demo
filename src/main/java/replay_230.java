import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lxy on 2017/12/1.
 */
//@Data
public class replay_230 implements Serializable{
    private BigDecimal discountRate;
    private String resultCode;
    private Integer discountRange;
    private Integer orderStatus;
    private String shopOrderFlowNo;
    private String shopOrderKey;
    private String resultMsg;
    private String tableName;
    private BigDecimal minConsumptionAmount;
    private List<SelectPromotion>  selectPromotionLst;
    private BigDecimal foodAmount;
    private SelectCardInfo selectCardInfo;
    private BigDecimal paidAmount;
    private List<Food> foodLst;
}
