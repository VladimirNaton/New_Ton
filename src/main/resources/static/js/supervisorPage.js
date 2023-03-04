$(document).ready(function () {

    let idSelectedInProduction = '';
    let idSelectedTaskShift = '';

    let productForProductionTable = $('#product-for-production-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "paging": false,
        "info": false,
        scrollX: true,
        "ajax": {
            "url": "/search/get-data-for-product-for-production-table",
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
                "targets": 10,
                "orderable": false
            }

        ]
    });


    let shiftTaskTable = $('#shift-task-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "paging": false,
        "info": false,
        scrollX: true,
        "ajax": {
            "url": "/search/get-data-for-task-shift-table",
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
                "targets": 10,
                "orderable": false
            }

        ]
    });


    function clearData() {
        idSelectedInProduction = '';
        idSelectedTaskShift = '';
        $('#product-in-production-head-brend').val('');
        $('#product-in-production-head-date-create').val('');
        $('#product-in-production-head-name-prod').val('');
        $('#temp-min').val('');
        $('#temp-max').val('');
        $('#common-weight-product-in-production').val('');

    }

    $('#product-for-production-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            clearData();
        } else {
            productForProductionTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            idSelectedInProduction = productForProductionTable.row(this).data().idpr;
            getDataForHeaderInformationString();
        }
    });

    $('#shift-task-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            clearData();
        } else {
            shiftTaskTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            idSelectedTaskShift = shiftTaskTable.row(this).data().idpr;
            getDataForHeaderInformationString();
        }
    });

    function getDataForHeaderInformationString() {
        if (idSelectedInProduction !== '') {
            $.ajax({
                url: '/search/get-data-for-head-string-edite-recipe/' + idSelectedInProduction,
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
            productForProductionTable.ajax.reload();
            shiftTaskTable.ajax.reload();
            countDownTimer.setMinutes(countDownTimer.getMinutes() + 5);
        }
    }, 1000);

    $('#sent-to-shift-task').click(function () {
        if (idSelectedInProduction !== '') {
            $.ajax({
                url: '/update/send-to-task-shift/' + idSelectedInProduction,
                method: 'put',
                success: function (data) {
                    clearData();
                    productForProductionTable.ajax.reload();
                    shiftTaskTable.ajax.reload();
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });
        } else {
            alert("Вы не выбрали ни одного элемента !!!!");
        }
    })

    $('#send-to-product-in-production').click(function () {
        if (idSelectedTaskShift !== '') {
            $.ajax({
                url: '/update/send-to-product-in-production/' + idSelectedTaskShift,
                method: 'put',
                success: function (data) {
                    clearData();
                    productForProductionTable.ajax.reload();
                    shiftTaskTable.ajax.reload();
                },
                beforeSend: function () {
                },
                complete: function () {
                },
                error: function (xhr, status, error) {
                }
            });
        } else {
            alert("Вы не выбрали ни одного элемента !!!!");
        }
    })




})