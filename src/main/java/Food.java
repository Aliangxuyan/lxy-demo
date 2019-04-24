import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lxy on 2017/12/1.
 */
public class Food implements Serializable{
    private String promotionIDLst;  // TODO String 类型还是集合
    private BigDecimal payPriceMember;
    private String remark;
    private BigDecimal originPrice;
    private int isDiscount;
    private int isSFDetail;
    private int isBatching;
    private String unitKey;
    private String foodUnitID;
    private BigDecimal duePrice;
    private BigDecimal number;
    private String foodName;
    private BigDecimal foodCount;
    private BigDecimal payPrice;
    private int isAffectedByPromotion;
    private BigDecimal vipPrice;
    private int isGift;
    private String foodUnit;
    private String orderKey;
    private String foodID;
    private String foodCategoryName;
    private int isSetFood;
    private int IsWaitConfirmFoodNumber;
    private String unit;
    private String SFMUnitCode;
    private String foodCode;

}
