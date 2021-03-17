/**
 * 项目全局uedior插件封装
 *
 * @param $ 引用对象
 */
(function ($) {
    $.extend({

        /**
         * 初始化ueditor
         */
        ueditor: {
            _ue: {},

            init: function(options) {
                // 覆盖editor默认上传
                UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
                UE.Editor.prototype.getActionUrl = function(action) {
                    console.log("当前ueditor的action事件：" + action);

                    // 根据不同的事件类型，自定义请求后端接口
                    if(action == 'uploadimage' || action == 'uploadfile') {
                        return window.CTX+"/common/ueditor/uploadEditor/"+action;
                    } else if (action == 'catchimage') {
                        return window.CTX+"/common/ueditor/catchEditor";
                    } else {
                        return this._bkGetActionUrl.call(this, action); // 百度编辑器默认的action
                    }
                }
                var ue = UE.getEditor(options.container || 'container', {
                    wordCount: false, //关闭字数统计
                    autoHeightEnabled: false, // 自动高度
                    elementPathEnabled: false, //关闭elementPath
                    allowDivTransToP: false, // 关闭div变成p标签
                    autoFloatEnabled: false, // 关闭高度浮动
                    initialFrameWidth: options.width || 900, // 初始化编辑器宽度
                    initialFrameHeight: options.height || 400 // 初始化编辑器宽度
                });
                // ue.ready(function() {
                //     ue.setContent(options.default || '请输入内容'); // 初始化内容
                //     ue.addListener("focus", function() { // 文本框获取焦点时清空默认显示的内容
                //         ue.setContent('');
                //     });
                //     ue.addListener("blur", function() { // 文本框是去焦点时,若内容为空则显示默认显示的内容
                //         if(!ue.getContent()){
                //             ue.setContent(options.default || '请输入内容');
                //         }
                //     });
                // });

                // 将ue实例对象返回，供业务操作使用
                _ue = ue;
            },

            /**
             * 返回编辑器内容
             */
            getContent: function() {
                return _ue.getContent();
            },

            /**
             * 检验编辑器内容是否为空
             */
            isEmpty: function() {
                return !_ue.hasContents();
            }
        }
    });
})(jQuery);
