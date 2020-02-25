package com.lxy.test.basic.java8;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * @author lxy
 * @date 2019-05-06
 * LocalDate、LocalTime、LocalDateTime 类的实 例是不可变的对象，分别表示使用 ISO-8601日 历系统的日期、时间、日期和时间。它们提供 了简单的日期或时间，并不包含当前的时间信 息。也不包含与时区相关的信息
 * <p>
 * Instant
 * 用于“时间戳”的运算。它是以Unix元年(传统 的设定为UTC时区1970年1月1日午夜时分)开始 所经历的描述进行运算
 * <p>
 * Duration 和 Period
 *  Duration:用于计算两个“时间”间隔  Period:用于计算两个“日期”间隔
 *
 *
 *  日期的操纵
 *  TemporalAdjuster : 时间校正器。有时我们可能需要获 取例如:将日期调整到“下个周日”等操作。
 *  TemporalAdjusters : 该类通过静态方法提供了大量的常 用 TemporalAdjuster 的实现。
 * 例如获取下个周日:
 *
 * 解析与格式化:
 * java.time.format.DateTimeFormatter 类:该类提供了三种 格式化方法:
 *  预定义的标准格式  语言环境相关的格式  自定义的格式
 *
 *  时区的处理
 *  Java8 中加入了对时区的支持，带时区的时间为分别为: ZonedDate、ZonedTime、ZonedDateTime
 * 其中每个时区都对应着 ID，地区ID都为 “{区域}/{城市}”的格式 例如 :Asia/Shanghai 等
 * ZoneId:该类中包含了所有的时区信息 getAvailableZoneIds() : 可以获取所有时区时区信息 of(id) : 用指定的时区信息获取 ZoneId 对象
 */
public class DateTest {

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalTime.MAX);

        // **************************************
        System.out.println(LocalDate.of(2019, 05, 06));

        // ***********************∂**************
        System.out.println(Instant.now());

        // ***********************∂**************
        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(nextSunday);

    }


}
