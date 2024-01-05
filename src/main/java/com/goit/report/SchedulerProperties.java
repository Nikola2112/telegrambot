package com.goit.report;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Value
@ConfigurationProperties("telegram.bot.scheduler")
public class SchedulerProperties {
    String cron;
}
