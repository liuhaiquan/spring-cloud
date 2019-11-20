package com.kavin.feign.interfaces;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kavin.core.data.DataObject;

@FeignClient("ucenter")  //此处的值是需要调用的服务名称，也就是applicaiton.yml 文件中的spring.application.name的值
public interface UcenterFeignClient {
	@RequestMapping(value = "/{model}/{method}")//这里是需要调用的服务的mapping映射地址。@PathVariable得设置value（坑）
	                                             //@PathVariable注解把方法中的参数传递给mapping映射地址中的参数
	                                               //@RequestBody 把方法的参数dataObject当作参数传递给调用的服务接口。
	public DataObject commonAjax(@PathVariable("model") String model,@PathVariable("method") String method,@RequestBody DataObject dataObject);
	
	
}
