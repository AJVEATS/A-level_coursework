function pageLoad() {
    $.ajax({
        url: 'userQuizzes/list',
        type: 'GET',
        success: profileList => {
            let profileOverviewHTML = `<div class="container">`
                + `<div class="row mb-2">`
                + `<div class="col-3 bg-dark font-weight-bold">User</div>`
                + `<div class="col-3 bg-dark font-weight-bold">Topics Completed</div>`
                + `<div class="col-3 bg-dark font-weight-bold">Highest Score</div>`
                + `<div class="col-3 bg-dark font-weight-bold">Recent Score</div>`
                + `</div>`;
            for (let userQuizzes of profileList) {
                profileOverviewHTML += `<div class="row mb-2">`
                    + `<div class="col-3">${userQuizzes.User}</div>`
                    + `<div class="col-3">${userQuizzes.TopicCompleted}</div>`
                    + `<div class="col-3">${userQuizzes.HighestScore}</div>`
                    + `<div class="col-3">${userQuizzes.RecentScore}</div>`
                    + `</div>`;
            }
            profileOverviewHTML += `<div>`;
            $('#userQuiz').html(profileOverviewHTML);
        }
    });
}
