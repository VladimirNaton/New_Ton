$(document).ready(function () {

    let idSelectedElement = '';
    let productState = $("#filter-select option:selected").val();

    let productInProductionTable = $('#product-in-production-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "paging": false,
        "info": false,
        "ajax": {
            "url": "/search/get-data-for-product-in-production-supervisor-table",
            "type": "post",
            data: function (data) {
                data.state = productState;
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
        } else {
            productInProductionTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            idSelectedElement = productInProductionTable.row(this).data().idpr;
            getDataForHeaderInformationString();
        }
    });

    function getDataForHeaderInformationString() {
        if (idSelectedElement !== '') {
            $.ajax({
                url: '/search/get-data-for-head-string-edite-recipe/' + idSelectedElement,
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
            let url = './recipe-in-production-supervisor?idProd=' + idSelectedElement;
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

    $('#filter-select').change(function () {
        productState = $("#filter-select option:selected").val();
        productInProductionTable.ajax.reload();
    })



})