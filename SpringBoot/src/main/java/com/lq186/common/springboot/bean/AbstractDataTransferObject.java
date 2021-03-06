/*    
    Copyright ©2019 lq186.com 
 
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
 
        http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
/*
    FileName: AbstractDataTransferObject.java
    Date: 2019/3/11
    Author: lq
*/
package com.lq186.common.springboot.bean;

import com.lq186.common.bean.EntityId;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractDataTransferObject<T extends EntityId, DTO extends AbstractDataTransferObject> implements DataTransferObject<T, DTO> {

    public T toEntity() throws RuntimeException {
        Class<T> classOfT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            T instanceOfT = classOfT.newInstance();
            BeanUtils.copyProperties(this, instanceOfT);
            this.toEntityAfterProperties(instanceOfT);
            return instanceOfT;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DTO fromEntity(T entity) {
        BeanUtils.copyProperties(entity, this);
        this.fromEntityAfterProperties(entity);
        return (DTO) this;
    }

    protected void fromEntityAfterProperties(T entity) {

    }

    protected void toEntityAfterProperties(T entity) {

    }
}
