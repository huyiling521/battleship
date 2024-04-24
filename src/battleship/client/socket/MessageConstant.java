package battleship.client.socket;

public class MessageConstant {
    //constant for sending message
    public static final String RESPONSE = "RESPONSE:";
    public static final String REQUEST = "REQUEST:";
    public static final String NOTIFICATION = "NOTE:";
    public static final String OPPONENT_ATTACK = "OPPONENT_ATTACK:";
    public static final String OPPONENT_MESSAGE = "OPPONENT_MESSAGE:";
    public static final String OPPONENT_NAME = "OPPONENT_NAME:";
    public static final String SYSTEM_MESSAGE = "SYSTEM_MESSAGE:";
    public static final String GAME_START = "GAME_START:";
    public static final String GAME_END = "GAME_END:";
    public static final String CONNECTION = "CONNECTION:";
    public static final String SUCCESS = "SUCCESS:";
    public static final String ERROR = "ERROR:";

    //constant for receiving message
    public static final String SETTING = "MY_SETTING:";
    public static final String ATTACK = "MY_ATTACK:";
    public static final String MESSAGE = "MY_MESSAGE:";
    public static final String NAME_SETTING = "MY_NAME:";
    public static final String UNKNOWN_ERROR = "Unknown error";
    public static final String USER_NOT_LOGIN = "用户未登录";
    public static final String CATEGORY_BE_RELATED_BY_SETMEAL = "Deletion failed. Category is connected to meals.";
    public static final String CATEGORY_BE_RELATED_BY_DISH = "Deletion failed. Category is connected to dishes.";
    public static final String SHOPPING_CART_IS_NULL = "购物车数据为空，不能下单";
    public static final String ADDRESS_BOOK_IS_NULL = "用户地址为空，不能下单";
    public static final String LOGIN_FAILED = "登录失败";
    public static final String UPLOAD_FAILED = "文件上传失败";
    public static final String SETMEAL_ENABLE_FAILED = "套餐内包含未启售菜品，无法启售";
    public static final String PASSWORD_EDIT_FAILED = "密码修改失败";
    public static final String DISH_ON_SALE = "起售中的菜品不能删除";
    public static final String SETMEAL_ON_SALE = "起售中的套餐不能删除";
    public static final String DISH_BE_RELATED_BY_SETMEAL = "当前菜品关联了套餐,不能删除";
    public static final String ORDER_STATUS_ERROR = "订单状态错误";
    public static final String ORDER_NOT_FOUND = "订单不存在";
}
