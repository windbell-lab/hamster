/**  
* @Title: package-info.java
* @Package org.windbell.lab.hamster.mq.rabbit.WorkQueues
* @Description: 使用场景：一个发送端，多个接收端，如分布式的任务派发。
* 				为了保证消息发送的可靠性，不丢失消息，使消息持久化了。
* 				同时为了防止接收端在处理消息时down掉，只有在消息处理完成后才发送ack消息。
* @author MN 
* @date 2016年7月10日 下午10:45:40
* @version V1.0  
*/
/**
* @ClassName: package-info
* @Description: TODO
* @author MN
* @date 2016年7月10日 下午10:45:40
*
*/
package org.windbell.lab.hamster.mq.rabbit.WorkQueues;