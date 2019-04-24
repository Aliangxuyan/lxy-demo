import java.io.Serializable;
import java.util.List;

/**
 * Created by lxy on 2017/12/1.
 */
public class SelectPromotion implements Serializable{

    private List<String>  cardIDList;
    private List<PromotionFood> foodLst;
    private Integer programType;
    private String promotionAmount;
    private List<Promotion> promotions;
    private String receivableAmount;
    private List<SelectPresentFood>  selectPresentFoodList;

}
