package org.apache.dubbo.springboot.demo.model.vo;

import lombok.Data;

/**
 * @author caijizhou
 * @date 2023/10/11 10:40
 * 接收修改昵称时前端返回的json对象
 */
@Data
public class NewNickname {
    private String nickname;
}
