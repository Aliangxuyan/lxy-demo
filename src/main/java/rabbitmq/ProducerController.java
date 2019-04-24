package rabbitmq;

/**
 * Created by lxy on 2018/7/1.
 */
//public class ProducerController {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @GetMapping("/sendMessage")
//    public Object sendMessage() {
//        new Thread(() -> {
//            for (int i = 0; i < 100; i++) {
//                String value = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
//                Console.log("send message {}", value);
//                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, value);
//            }
//        }).start();
//        return "ok";
//    }
//
//}
