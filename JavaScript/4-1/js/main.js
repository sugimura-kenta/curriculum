var app = new Vue({
    el: '#app',
    data: {
        list: [],
        addText: '',
        ischecked: false,
        lists:0,
        checked:0,
        search:'',
    },
    //watchでlistの変更を監視
    watch: {
        list: {
            handler: function() {
                //localStorageにデータを保存
                localStorage.setItem("list", JSON.stringify(this.list));
            },
            deep: true
        }
    },
    //マウントされた時にlocalStorageからデータを取得
    mounted: function() {
        this.list = JSON.parse(localStorage.getItem("list")) || [];
    },
    methods: {
        addToDo: function() {
            if (this.addText !== '') {
                this.list.push({
                    text: this.addText, 
                    isChecked: false,
                });
            }
            this.addText = '';
        },
        deleteBtn: function() {
            this.list = this.list.filter(function(todo) {
                return !todo.isChecked;
            });
        }
    },

   computed:{
        listlenght:function(){
            //残りのタスク量を調べる算出プロパティ 
            //listがObjectなことに注意。
            //filterにてチェックについていないものを探す。
            checked = this.list.filter(function(todo){return !todo.isChecked}).length
            lists = Object.keys(this.list).length
            return checked + '/' + lists
        },
           
        searchlist:function(){
            // console.log(this.list[1].text);
            // console.log(this.search);  ここでは正常に値が出る。
            // return this.list.filter(function (value) { 
            //     console.log(value.text);
            //     console.log(this.search);ここでundefiedになってしまう。
            //     console.log(String(value.text).indexOf(this.search));
            //     return String(value.text).indexOf(this.search) !== -1;
            // })
            

            //完成品
            let slist = [];
            let word = '';
            for(let i = 0; i < Object.keys(this.list).length; i++){
                word = this.list[i].text;
                if(String(word).indexOf(this.search) !== -1){
                    slist.push(this.list[i]);
                }
            }
            console.log(this.search);
            return slist

        }
    }
});