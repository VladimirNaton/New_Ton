$(document).ready(function () {

    let searchValue = '';
    let idMain = '';

    let accountManagerTable = $('#account-manager-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/search/account-manager-table-data",
            "type": "post",
            data: function (data) {
                data.searchValue = searchValue;
            }
        },
        "lengthMenu": [[10, 15, 20], [10, 15, 20]],
        "columns": [
            {"data": "idpr"},
            {"data": "strDate"},
            {"data": "brend"},
            {"data": "nameprod"},
            {"data": "percent"},
            {"data": "mass"},
            {"data": "tempprodmin"},
            {"data": "tempprodmax"},
            {"data": "comment"}
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
            }
        ]
    });

    function clearData() {
        $('#head-brend').val('');
        $('#head-date-create').val('');
        $('#head-name-prod').val('');
        $('#temp-min').val('');
        $('#temp-max').val('');
        $('#common-weight').val('');
        $('#control').val('');
        idMain = '';
    }

    $('#account-manager-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            clearData();
            deleteErrorColorByControl();

        } else {
            accountManagerTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            idMain = elem.find("td:first").html();
            getDataForHeaderInformationString();
        }
    });


    function getDataForHeaderInformationString() {
        if (idMain !== '') {
            $.ajax({
                url: '/search/get-data-for-head-string-edite-recipe/' + idMain,
                method: 'get',
                success: function (data) {
                    $('#head-brend').val(data.brend);
                    $('#head-date-create').val(data.dateString);
                    $('#head-name-prod').val(data.nameProd);
                    $('#temp-min').val(data.tempMin);
                    $('#temp-max').val(data.tempMax);
                    $('#common-weight').val(data.mass);
                    $('#control').val(data.percent);
                    if (Number(data.percent) !== 100) {
                        addErrorColorByControl();
                    } else {
                        deleteErrorColorByControl();
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

    $('#search-prod').click(function () {
        searchValue = $('#name-prod').val();
        accountManagerTable.ajax.reload();
    })

    $('#resetSearch').click(function () {
        searchValue = '';
        $('#name-prod').val('');
        accountManagerTable.ajax.reload();
    })

    $('#update-table').click(function () {
        accountManagerTable.ajax.reload();
    })


    function addErrorColorByControl() {
        $('#control').css('background-color', 'crimson');
    }

    function deleteErrorColorByControl() {
        $('#control').css('background-color', 'white');
    }

    $('#check-recipe').click(function () {
        if (idMain !== '') {
            let url = './get-account-manager-check-recipe-page?idProd=' + idMain;
            window.open(url, '_blank');
        } else {
            alert("Вы не выбрали ни одной записи !!!");
        }
    })

    $('#directory-editor').click(function () {
        let url = './edite-catalog-page';
        window.open(url, '_blank');
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
            accountManagerTable.ajax.reload();
            countDownTimer.setMinutes(countDownTimer.getMinutes() + 5);
        }
    }, 1000);


})