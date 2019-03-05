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
    FileName: PageRequestHolder.java
    Date: 2019/3/4
    Author: lq
*/
package com.lq186.tools.pageable;

import com.lq186.tools.bean.PageRequest;
import com.lq186.tools.util.NumberUtils;

public final class PageRequestHolder {

    private static final long DEFAULT_PAGE = 1;

    private static final int DEFAULT_SIZE = 10;

    /**
     * 保存分页请求信息到当前线程中
     *
     * @param pageString 页码
     * @param sizeString 页面大小
     * @param sort       排序字段
     * @param direction  排序方向
     */
    public static final void setPageRequest(String pageString, String sizeString, String sort, String direction) {
        long page = NumberUtils.longFromString(pageString, DEFAULT_PAGE);
        int size = NumberUtils.intFromString(sizeString, DEFAULT_SIZE);
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(page);
        pageRequest.setSize(size);
        pageRequest.setSort(sort);
        pageRequest.setDirection(direction);
        InheritableThreadLocalHolder.PAGE_REQUEST_HOLDER.set(pageRequest);
    }

    /**
     * 从当前线程中获取分页请求信息
     *
     * @return 分页请求实例，非空
     */
    public static final PageRequest getPageRequest() {
        PageRequest pageRequest = InheritableThreadLocalHolder.PAGE_REQUEST_HOLDER.get();
        if (null == pageRequest) {
            pageRequest = new PageRequest();
            pageRequest.setPage(DEFAULT_PAGE);
            pageRequest.setSize(DEFAULT_SIZE);
            pageRequest.setSort("");
            pageRequest.setDirection("");
        }
        return pageRequest;
    }

    static class InheritableThreadLocalHolder {
        static final InheritableThreadLocal<PageRequest> PAGE_REQUEST_HOLDER = new InheritableThreadLocal<PageRequest>();
    }
}
