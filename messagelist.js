$(function () {
    /*
    <tr>
        <td>obj.id</td>
        ...
    </tr>
     */
    setTimeout(function() {
        $.get("messagelist", function (data) {
            // 怎么遍历 js 的数组
            let tbody = $("tbody");

            for (i in data) {
                let message = data[i];

                // message.id
                // message["id"]   这两个等价
                // message["hello-world"]  message.hello-world
                let html = "<tr>" +
                    "<td>" + message.id + "</td>" +
                    "<td>" + message.who + "</td>" +
                    "<td>" + message.when + "</td>" +
                    "<td>" + message.what + "</td>" +
                    "</tr>";

                tbody.append(html);//添加到html的tbody中
            }
        });
    },);
});