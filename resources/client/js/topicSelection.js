function pageLoad() {
    getTopicList();

}

function getTopicList() {
    console.log("Invoked getTopicList() ");

    $.ajax({
        type: "GET",
        url: "/topic/list",
        success: response => {
            if (response.toString().startsWith('Error:')) {
                alert(response);
                window.open("login.html", "_self");
            } else {
                formatTopicList(response);
            }
        }
    });
}

function formatTopicList(data) {
    console.log(data);
    let dataHTML ="";
    for (let item of data) {
        dataHTML += `<br>` + `<div class="topic"><span class="topicTitle"> ${item.topic}</span> <br>` + `<br>` +
            `<span class="topicDescription">${item.topicDescription}</span> <button class="goToTopic btn btn-lg btn-dark" data-topic="${item.topic}">Go to Quizzes</button></div>` + `<br>`;
    }
    $('#topicList').html(dataHTML);

    $(".goToTopic").click(event => {
        const topic = $(event.target).attr('data-topic');  //from the click event, get the topic
        sessionStorage.setItem("topic", topic);
        window.open("topic.html", "_self");
    });

}