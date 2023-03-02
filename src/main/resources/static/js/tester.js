$(document).ready(function () {

    let idSelectedElement = '';
    let rawId = '';

    let productInProductionTable = $('#product-in-production-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "paging": false,
        "info": false,
        "ajax": {
            "url": "/search/get-data-for-tester-table",
            "type": "post",
            data: function (data) {
            }
        },
        "columns": [
            {"data": "idpr"},
            {"data": "datecrStr"},
            {"data": "dateplStr"},
            {"data": "brend"},
            {"data": "nameprod"},
            {"data": "percent"},
            {"data": "mass"},
            {"data": "tempprodmin"},
            {"data": "tempprodmax"},
            {"data": "comment"},
            {"data": "stateStr"}
        ],
        "columnDefs": [
            {
                "targets": 0,
                "orderable": false
            },
            {
                "targets": 1,
                "orderable": false
            },
            {
                "targets": 2,
                "orderable": false
            },
            {
                "targets": 3,
                "orderable": false
            },
            {
                "targets": 4,
                "orderable": false
            },
            {
                "targets": 5,
                "orderable": false
            },
            {
                "targets": 6,
                "orderable": false
            },
            {
                "targets": 7,
                "orderable": false
            },
            {
                "targets": 8,
                "orderable": false
            },
            {
                "targets": 9,
                "orderable": false
            },
            {
                "targets": 9,
                "orderable": false
            }

        ]
    });


    function clearData() {
        idSelectedElement = '';
        $('#product-in-production-head-brend').val('');
        $('#product-in-production-head-date-create').val('');
        $('#product-in-production-head-name-prod').val('');
        $('#temp-min').val('');
        $('#temp-max').val('');
        $('#common-weight-product-in-production').val('');
    }

    $('#product-in-production-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            clearData();
            $('#confirm-template').hide();
            $('#start-time').text('');
            $('#min-to-work').text('');
            $('#sec-to-work').text('');
            rawId = '';

        } else {
            $('#confirm-template').hide();
            $('#start-time').text('');
            productInProductionTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            idSelectedElement = productInProductionTable.row(this).data().idpr;
            getDataForHeaderInformationString();
            checkTakeTemplate();
        }
    });

    function getDataForHeaderInformationString() {
        if (idSelectedElement !== '') {
            $.ajax({
                url: '/search/get-data-for-tester-head-string/' + idSelectedElement,
                method: 'get',
                success: function (data) {
                    $('#product-in-production-head-brend').val(data.brend);
                    $('#product-in-production-head-date-create').val(data.dateString);
                    $('#product-in-production-head-name-prod').val(data.nameProd);
                    $('#temp-min').val(data.tempMin);
                    $('#temp-max').val(data.tempMax);
                    $('#common-weight-product-in-production').val(data.mass);
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });
        }
    }

    $('#look-recipe-product-in-production').click(function () {
        if (idSelectedElement !== '') {
            let url = './recipe-in-production-tester?idProd=' + idSelectedElement;
            window.open(url, '_blank');
        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    })

    let countDownTimer = new Date();
    countDownTimer.setMinutes(countDownTimer.getMinutes() + 5);

    let timer = setInterval(function () {
        let now = new Date().getTime();
        let distance = countDownTimer - now;
        let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        let seconds = Math.floor((distance % (1000 * 60)) / 1000);
        document.getElementById("timer").innerHTML = minutes + "m " + seconds + "s ";
        if (distance < 1000) {
            clearData();
            productInProductionTable.ajax.reload();
            countDownTimer.setMinutes(countDownTimer.getMinutes() + 5);
        }
    }, 1000);


    $('#return-to-work').click(function () {
        if (idSelectedElement !== '') {
            $.ajax({
                url: '/update/return-to-work/' + idSelectedElement,
                method: 'put',
                success: function (data) {
                    if (data) {
                        alert("Рецепт отправлен в работу !!!");
                        idSelectedElement = '';
                        productInProductionTable.ajax.reload();
                        clearData();
                    } else {
                        alert("Возникла ошибка при отправке рецепта в работу !!!");
                    }
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });

        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    })

    $('#in-reject').click(function () {
        if (idSelectedElement !== '') {
            $.ajax({
                url: '/update/send-in-reject/' + idSelectedElement,
                method: 'put',
                success: function (data) {
                    if (data) {
                        alert("Рецепт отправлен в брак !!!");
                        idSelectedElement = '';
                        productInProductionTable.ajax.reload();
                        clearData();
                    } else {
                        alert("Возникла ошибка при отправке рецепта в брак !!!");
                    }
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });

        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    })


    $('#put-aside').click(function () {
        if (idSelectedElement !== '') {
            $.ajax({
                url: '/update/send-put-aside/' + idSelectedElement,
                method: 'put',
                success: function (data) {
                    if (data) {
                        alert("Рецепт отложен !!!");
                        idSelectedElement = '';
                        productInProductionTable.ajax.reload();
                        clearData();
                    } else {
                        alert("Возникла ошибка при отправке рецепта !!!");
                    }
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });

        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    })

    $('#send-comment').click(function () {
        if (idSelectedElement !== '') {
            let comment = $('#comment-to-operator').val();
            let data = {
                "comment": comment,
                "id": idSelectedElement
            }
            $.ajax({
                url: '/update/send-comment',
                method: 'put',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                success: function (data) {
                    if (data) {
                        alert("Коментарий сохранен !!!");
                        idSelectedElement = '';
                        productInProductionTable.ajax.reload();
                        clearData();
                        $('#comment-to-operator').val('');
                    } else {
                        alert("Возникла ошибка при сохранении коментария !!!");
                    }
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });

        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    })

    function checkTakeTemplate() {
        if (idSelectedElement !== '') {
            $.ajax({
                url: '/search/check-take-template/' + idSelectedElement,
                method: 'get',
                success: function (data) {
                    if (data !== '') {
                        $('#confirm-template').show();
                        $('#start-time').text(data.time);
                        rawId = data.id;
                        createTimerTemplate(data.time);
                    }
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });
        }
    }

    $('#close-button').click(function () {
        $('#confirm-template').hide();
        $('#start-time').text('');
        $('#min-to-work').text('');
        $('#sec-to-work').text('');
        rawId = '';
        clearInterval(timerTemplate);
    })
    let timerTemplate;

    function createTimerTemplate(startTime) {
        let startTemplateTime = new Date(startTime);
        timerTemplate = setInterval(function () {
            let now = new Date().getTime();
            let distance = now - startTemplateTime;
            let minutes = Math.floor((distance / 1000) / 60);
            let seconds = Math.floor((distance % (1000 * 60)) / 1000);
            document.getElementById("min-to-work").innerHTML = minutes;
            document.getElementById("sec-to-work").innerHTML = seconds + "s ";
            document.getElementById("symbol").innerHTML = "m ";
        }, 1000);
    }

    $('#confirm-button').click(function () {
        if (idSelectedElement !== '' && rawId !== '') {
            let time = $('#min-to-work').text();

            let data = {
                "time": time,
                "idMain": idSelectedElement,
                "rawId": rawId
            }

            $('#confirm-template').hide();
            $('#start-time').text('');
            $('#min-to-work').text('');
            $('#sec-to-work').text('');
            rawId = '';

            $.ajax({
                url: '/update/save-time',
                method: 'put',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                success: function (data) {
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });
        }
    })

    $('#tester-protocol-button').click(function () {
        if (idSelectedElement !== '') {
            let url = './get-product-protocol/' + idSelectedElement;
            window.open(url, '_blank');
        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    })

})