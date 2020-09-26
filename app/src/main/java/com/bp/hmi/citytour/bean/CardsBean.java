package com.bp.hmi.citytour.bean;

import java.util.List;

/**
 * <pre>
 *     author : BaiPengMac
 *     e-mail : xxx@xx
 *     time   : 2020/08/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CardsBean {

    /**
     * code : 1
     * msg : 操作成功
     * result : {"pageSize":10,"currentPage":0,"items":[{"id":2,"name":"星巴克 季节限定茶饮立减12元","discount":null,"summary":"季节限定茶饮立减12元","expire":"2020-10-01 00:00:00","icon":null,"createTime":"2020-09-13 20:19:59","issuance":3,"issuanceStr":"已发布","seq":null},{"id":3,"name":"星巴克   指定星杯等商品7折","discount":7,"summary":"星巴克   指定星杯等商品7折","expire":"2020-09-30 00:00:00","icon":null,"createTime":"2020-09-13 20:23:19","issuance":3,"issuanceStr":"已发布","seq":null},{"id":4,"name":"麦当劳   5元满减红包","discount":null,"summary":"麦当劳   5元满减红包","expire":"2020-10-16 00:00:00","icon":null,"createTime":"2020-09-13 20:24:59","issuance":3,"issuanceStr":"已发布","seq":null},{"id":5,"name":"Lamer 满1000元减100代金券","discount":null,"summary":"满1000元减100代金券","expire":"2020-12-23 00:00:00","icon":null,"createTime":"2020-09-13 20:26:31","issuance":3,"issuanceStr":"已发布","seq":null},{"id":6,"name":"Maje     早秋新品9.5折","discount":9.5,"summary":"Maje     早秋新品9.5折","expire":"2020-10-23 00:00:00","icon":null,"createTime":"2020-09-13 20:26:53","issuance":3,"issuanceStr":"已发布","seq":null}],"totalPage":1,"totalCount":5}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * pageSize : 10
         * currentPage : 0
         * items : [{"id":2,"name":"星巴克 季节限定茶饮立减12元","discount":null,"summary":"季节限定茶饮立减12元","expire":"2020-10-01 00:00:00","icon":null,"createTime":"2020-09-13 20:19:59","issuance":3,"issuanceStr":"已发布","seq":null},{"id":3,"name":"星巴克   指定星杯等商品7折","discount":7,"summary":"星巴克   指定星杯等商品7折","expire":"2020-09-30 00:00:00","icon":null,"createTime":"2020-09-13 20:23:19","issuance":3,"issuanceStr":"已发布","seq":null},{"id":4,"name":"麦当劳   5元满减红包","discount":null,"summary":"麦当劳   5元满减红包","expire":"2020-10-16 00:00:00","icon":null,"createTime":"2020-09-13 20:24:59","issuance":3,"issuanceStr":"已发布","seq":null},{"id":5,"name":"Lamer 满1000元减100代金券","discount":null,"summary":"满1000元减100代金券","expire":"2020-12-23 00:00:00","icon":null,"createTime":"2020-09-13 20:26:31","issuance":3,"issuanceStr":"已发布","seq":null},{"id":6,"name":"Maje     早秋新品9.5折","discount":9.5,"summary":"Maje     早秋新品9.5折","expire":"2020-10-23 00:00:00","icon":null,"createTime":"2020-09-13 20:26:53","issuance":3,"issuanceStr":"已发布","seq":null}]
         * totalPage : 1
         * totalCount : 5
         */

        private int pageSize;
        private int currentPage;
        private int totalPage;
        private int totalCount;
        private List<ItemsBean> items;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : 2
             * name : 星巴克 季节限定茶饮立减12元
             * discount : null
             * summary : 季节限定茶饮立减12元
             * expire : 2020-10-01 00:00:00
             * icon : null
             * createTime : 2020-09-13 20:19:59
             * issuance : 3
             * issuanceStr : 已发布
             * seq : null
             */

            private int id;
            private String name;
            private Object discount;
            private String summary;
            private String expire;
            private Object icon;
            private String createTime;
            private int issuance;
            private String issuanceStr;
            private Object seq;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getDiscount() {
                return discount;
            }

            public void setDiscount(Object discount) {
                this.discount = discount;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getExpire() {
                return expire;
            }

            public void setExpire(String expire) {
                this.expire = expire;
            }

            public Object getIcon() {
                return icon;
            }

            public void setIcon(Object icon) {
                this.icon = icon;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getIssuance() {
                return issuance;
            }

            public void setIssuance(int issuance) {
                this.issuance = issuance;
            }

            public String getIssuanceStr() {
                return issuanceStr;
            }

            public void setIssuanceStr(String issuanceStr) {
                this.issuanceStr = issuanceStr;
            }

            public Object getSeq() {
                return seq;
            }

            public void setSeq(Object seq) {
                this.seq = seq;
            }
        }
    }
}

