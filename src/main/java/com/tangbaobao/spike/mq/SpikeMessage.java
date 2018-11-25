package com.tangbaobao.spike.mq;

import com.tangbaobao.spike.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tangxuejun
 * @version 2018-11-25 22:39
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpikeMessage {
    private User user;
    private long productId;
}
