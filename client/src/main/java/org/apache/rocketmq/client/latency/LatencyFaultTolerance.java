/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.rocketmq.client.latency;

/**
 * 延迟机制 接口规范
 * @param <T>
 */
public interface LatencyFaultTolerance<T> {
    /**
     * 更新失败条目
     * @param name  brokerName
     * @param currentLatency  消息发送故障延迟时间
     * @param notAvailableDuration 不可以持续时长,在这个时长内broker将被规避
     */
    void updateFaultItem(final T name, final long currentLatency, final long notAvailableDuration);

    /**
     * 判断broker是否可用
     * @param name brokerName
     * @return
     */
    boolean isAvailable(final T name);

    /**
     * 移除Fault条目,broker将重新参与路由计算
     * @param name brokerName
     */
    void remove(final T name);

    /**
     * 尝试从规避的broker中选取一个,如果没有则返回null
     * @return
     */
    T pickOneAtLeast();
}
