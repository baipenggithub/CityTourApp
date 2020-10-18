package com.bp.hmi.citytour.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : BaiPengMac
 *     e-mail : xxx@xx
 *     time   : 2020/09/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class HomeVideoBean implements Serializable {

    /**
     * code : 1
     * msg : 操作成功
     * result : {"pageSize":10,"currentPage":0,"items":[{"id":4,"name":"探店","cover":null,"video":"uploadCityWalk/video/13d5e230ec7c406e914a02ffc59f956b.m4v","summary":"探店","collectQty":1,"commentQty":5},{"id":5,"name":"微电影","cover":null,"video":"uploadCityWalk/video/76071c29a5b74272bebb42afad792ab4.m4v","summary":"微电影","collectQty":2,"commentQty":6},{"id":6,"name":"咖啡馆","cover":null,"video":"uploadCityWalk/video/e92d889b206a4a558148f428c0536813.m4v","summary":"咖啡馆","collectQty":3,"commentQty":7},{"id":7,"name":"美术馆","cover":null,"video":"uploadCityWalk/video/a7a2cf2738dd429290f5ab7ad1e20609.m4v","summary":"美术馆","collectQty":4,"commentQty":8}],"totalPage":1,"totalCount":4}
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

    public static class ResultBean implements Serializable {
        /**
         * pageSize : 10
         * currentPage : 0
         * items : [{"id":4,"name":"探店","cover":null,"video":"uploadCityWalk/video/13d5e230ec7c406e914a02ffc59f956b.m4v","summary":"探店","collectQty":1,"commentQty":5},{"id":5,"name":"微电影","cover":null,"video":"uploadCityWalk/video/76071c29a5b74272bebb42afad792ab4.m4v","summary":"微电影","collectQty":2,"commentQty":6},{"id":6,"name":"咖啡馆","cover":null,"video":"uploadCityWalk/video/e92d889b206a4a558148f428c0536813.m4v","summary":"咖啡馆","collectQty":3,"commentQty":7},{"id":7,"name":"美术馆","cover":null,"video":"uploadCityWalk/video/a7a2cf2738dd429290f5ab7ad1e20609.m4v","summary":"美术馆","collectQty":4,"commentQty":8}]
         * totalPage : 1
         * totalCount : 4
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

        public static class ItemsBean implements Serializable {
            /**
             * id : 4
             * name : 探店
             * cover : null
             * video : uploadCityWalk/video/13d5e230ec7c406e914a02ffc59f956b.m4v
             * summary : 探店
             * collectQty : 1
             * commentQty : 5
             */

            private int id;
            private String name;
            private Object cover;
            private String video;
            private String summary;
            private int collectQty;
            private int commentQty;

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

            public Object getCover() {
                return cover;
            }

            public void setCover(Object cover) {
                this.cover = cover;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getCollectQty() {
                return collectQty;
            }

            public void setCollectQty(int collectQty) {
                this.collectQty = collectQty;
            }

            public int getCommentQty() {
                return commentQty;
            }

            public void setCommentQty(int commentQty) {
                this.commentQty = commentQty;
            }
        }
    }
}
