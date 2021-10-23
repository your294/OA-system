## Axios异步通信

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div id="test">
    <div>{{info.name}}</div>
    <div>{{info.hobbies}}</div>
    <div>{{info.birthdate}}</div>


</div>
<script type="text/javascript">

    let vm = new Vue({
        el: '#test',
        data(){ //此处data并不是方法它是属性
            return {
                info: {
                    name: null,
                    hobbies: null,
                    birthdate: null

                }
            }
        },
        mounted() {
            axios.get('data.json').then(response=>this.info=response.data);
            //和上述代码对应
        }
    });

</script>

</body>
</html>
```
