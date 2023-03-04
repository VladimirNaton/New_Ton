$(document).ready(function () {

    let codeSelectedElemRecipeTable;
    let idSelectedElemRecipeTable = '';
    let sequenceNumberFirstValue = '';
    let idCommentToStage = '';
    let idMain = '';

    idMain = $('#id-edite-recipe').text();

    let editeRecipeTable = $('#edite-recipe-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "paging": false,
        "info": false,
        "ajax": {
            "url": "/search/data-for-tester-recipe-table",
            "type": "POST",
            data: function (data) {
                data.idMain = $('#id-edite-recipe').text();
            }
        },
        "columns": [
            {"data": "id"},
            {"data": "n"},
            {"data": "stage"},
            {"data": "code"},
            {"data": "nameraw"},
            {"data": "percent"},
            {"data": "mass"},
            {"data": "devper"},
            {"data": "devmass"},
            {"data": "turnmix"},
            {"data": "timemix"},
            {"data": "pastpart"},
            {"data": "strDate"}
        ],
        "columnDefs": [
            {
                "targets": 0,
                "orderable": false,
                "visible": false
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
            ,
            {
                "targets": 9,
                "orderable": false
            },
            {
                "targets": 10,
                "orderable": false
            },
            {
                "targets": 11,
                "orderable": false
            },
            {
                "targets": 12,
                "orderable": false
            }
        ]
    });


    $('#edite-recipe-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            codeSelectedElemRecipeTable = '';
            idSelectedElemRecipeTable = '';
            sequenceNumberFirstValue = '';
            clearInputData();
            hideAllParameter();
        } else {
            editeRecipeTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            let elem = $(this);
            codeSelectedElemRecipeTable = elem[0].cells[2].innerText;
            showRecipeComponentInformation(codeSelectedElemRecipeTable);
            idSelectedElemRecipeTable = editeRecipeTable.row(this).data().id;
            getDataForSelectedRowEditeRecipeTable(idSelectedElemRecipeTable);
        }
    });

    function getDataForHeaderInformationString() {
        let idProd = $('#id-edite-recipe').text();
        if (idProd !== '') {
            $.ajax({
                url: '/search/get-data-for-head-string-tester-recipe/' + idProd,
                method: 'get',
                success: function (data) {
                    $('#edite-recipe-head-brend').val(data.brend);
                    $('#edite-recipe-head-date-create').val(data.dateString);
                    $('#edite-recipe-head-name-prod').val(data.nameProd);
                    $('#temp-min').val(data.tempMin);
                    $('#temp-max').val(data.tempMax);
                    $('#common-weight-edite-recipe').val(data.mass);
                    $('#comment-technologist').val(data.comment);
                    checkCommonPercents();
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

    getDataForHeaderInformationString();

    function showRecipeComponentInformation(code) {
        switch (code) {
            case '1':
                hideAllParameter();
                showCodeParameterComponent();
                break;
            case '2':
                hideAllParameter();
                showCodeParameterPast();
                break;
            case '3':
                hideAllParameter();
                break;
            case '4':
                hideAllParameter();
                showFilterParameter();
                break;
            case '5':
                hideAllParameter();
                break;
            case '6':
                hideAllParameter();
                showSolventParameter();
                break;
            case '7':
                hideAllParameter();
                break;
            case '8':
                hideAllParameter();
                showMixParameter();
                break;
        }
    }

    function showCodeParameterComponent() {
        $('#percent').show();
        $('#mass').show();
        $('#infelicity-percent').show();
        $('#infelicity-mass').show();
        $('#percent-product-in-production').show();
        $('#mass-product-in-production').show();
        $('#infelicity-percent-product-in-production').show();
        $('#infelicity-mass-product-in-production').show();
    }

    function showCodeParameterPast() {
        $('#percent').show();
        $('#mass').show();
        $('#infelicity-percent').show();
        $('#infelicity-mass').show();
        $('#part-label').show();
        $('#part-date-label').show();
        $('#percent-product-in-production').show();
        $('#mass-product-in-production').show();
        $('#infelicity-percent-product-in-production').show();
        $('#infelicity-mass-product-in-production').show();
        $('#part-label-product-in-production').show();
        $('#part-date-label-product-in-production').show();
    }

    function hideAllParameter() {
        $('#percent').hide();
        $('#mass').hide();
        $('#mixing').hide();
        $('#mixing-time').hide();
        $('#filter').hide();
        $('#infelicity-percent').hide();
        $('#infelicity-mass').hide();
        $('#part-label').hide();
        $('#part-date-label').hide();

        $('#percent-product-in-production').hide();
        $('#mass-product-in-production').hide();
        $('#mixing-product-in-production').hide();
        $('#mixing-time-product-in-production').hide();
        $('#filter-product-in-production').hide();
        $('#infelicity-percent-product-in-production').hide();
        $('#infelicity-mass-product-in-production').hide();
        $('#part-label-product-in-production').hide();
        $('#part-date-label-product-in-production').hide();
    }

    function showFilterParameter() {
        $('#filter').show();
        $('#filter-product-in-production').show();
    }

    function showSolventParameter() {
        $('#mass').show();
        $('#mass-product-in-production').show();
    }

    function showMixParameter() {
        $('#mixing').show();
        $('#mixing-time').show();

        $('#mixing-product-in-production').show();
        $('#mixing-time-product-in-production').show();
    }

    function getDataForSelectedRowEditeRecipeTable(id) {
        $.ajax({
            url: '/search/get-data-for-selected-row-tester-recipe-table/' + id,
            method: 'get',
            success: function (data) {
                if (codeSelectedElemRecipeTable === '1') {
                    compliteComponentRowInformation(data);
                }
                if (codeSelectedElemRecipeTable === '2') {
                    complitePastRowInformation(data);
                }
                if (codeSelectedElemRecipeTable === '6') {
                    compliteSolventRowInformation(data);
                }
                if (codeSelectedElemRecipeTable === '8') {
                    compliteMixingRowInformation(data);
                }
                if (codeSelectedElemRecipeTable === '4') {
                    compliteFilterRowInformation(data);
                }
                if (codeSelectedElemRecipeTable === '3' || codeSelectedElemRecipeTable === '5' || codeSelectedElemRecipeTable === '7') {
                    compliteAnotherRowInformation(data);
                }

                checkMassAndInfelicityMass();
                sequenceNumberFirstValue = $('#sequence-number-input').val();
            },
            beforeSend: function () {
            },
            complete: function () {
            },
            error: function (xhr, status, error) {
            }
        });
    }

    function compliteComponentRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#percent-input').val(data.percent);
        $('#mass-input').val(data.mass);
        $('#infelicity-percent-input').val(data.devper);
        $('#infelicity-mass-input').val(data.devmass);
        $('#mass-input-product-in-production').val(data.factMass);
        $('#infelicity-mass-input-product-in-production').val(data.factMassDev);
    }

    function complitePastRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#percent-input').val(data.percent);
        $('#mass-input').val(data.mass);
        $('#infelicity-percent-input').val(data.devper);
        $('#infelicity-mass-input').val(data.devmass);
        $('#part').val(data.pastpart);
        $('#past-date').val(data.dateString);
        $('#mass-input-product-in-production').val(data.factMass);
        $('#infelicity-mass-input-product-in-production').val(data.factMassDev);
    }

    function compliteSolventRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#mass-input').val(data.mass);
    }

    function compliteMixingRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#mixing-input').val(data.turnmix);
        $('#mixing-time-input').val(data.timemix);
    }

    function compliteFilterRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        if (data.filter !== '') {
            $('#filter-select').val(data.filter).change();
        }
        $('#filter-select').attr('disabled', 'disabled');
    }

    function compliteAnotherRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
    }


    function checkMassAndInfelicityMass() {
        let massInfelicity = Number($('#infelicity-mass-input').val()).toFixed(2);
        let percentInfelicity = Number($('#infelicity-percent-input').val()).toFixed(2);
        let percent = Number($('#percent-input').val()).toFixed(2);
        let mass = Number($('#mass-input').val()).toFixed(2);
        let calculatedMass = calculateMass(percent).toFixed(2);
        if (calculatedMass !== mass) {
            $('#mass-input').css('background-color', 'crimson');
        } else {
            $('#mass-input').css('background-color', 'white');
        }

        let calculatedInfelicityMass = calculateInfelicityMass(percentInfelicity).toFixed(2);
        if (calculatedInfelicityMass !== massInfelicity) {
            $('#infelicity-mass-input').css('background-color', 'crimson');
        } else {
            $('#infelicity-mass-input').css('background-color', 'white');
        }
    }

    function calculateMass(percent) {
        let commonWeight = Number($('#common-weight-edite-recipe').val());
        let result = (commonWeight / 100) * percent;
        return result;
    }

    function calculateInfelicityMass(percent) {
        let commonWeight = Number($('#common-weight-edite-recipe').val());
        let result = (commonWeight / 100) * percent;
        return result;
    }


    editeRecipeTable.on('draw', function () {
        checkCommonPercents();
    });

    function getTableData() {
        let tableData = editeRecipeTable.rows().data();
        return tableData;
    }

    function calculatePercents() {
        let data = getTableData();
        let mass = 0;
        $.each(data, function (key, value) {
            mass += value.mass;
        });

        let commonWeight = Number($('#common-weight-edite-recipe').val()) / 100;
        return (mass / commonWeight).toFixed(2);
    }

    function checkCommonPercents() {
        let tableData = getTableData();
        let percents = calculatePercents(tableData);
        $('#control').val(percents);
        changeColorCommonPercentsInput(percents);
    }

    function changeColorCommonPercentsInput(percents) {
        if (percents < 100 || percents > 100) {
            $('#control').css('background-color', 'crimson');
        } else {
            $('#control').css('background-color', 'white');
        }
    }

    function clearInputData() {
        $('#sequence-number-input').val('');
        $('#stage-input').val('');
        $('#percent-input').val('');
        $('#mass-input').val('');
        $('#mixing-input').val('');
        $('#mixing-time-input').val('');
        // $('#filter-input').val('');
        $('#infelicity-percent-input').val('');
        $('#infelicity-mass-input').val('');
        $('#part').val('');
        $('#past-date').val('');
        $('#name-prod-input').val('');
        $('#mass-input-product-in-production').val('');
        $('#infelicity-mass-input-product-in-production').val('');
    }

    $('#save-comment-to-stage').click(function () {
        let stage = $('#stage').val();
        let comment = $('#comment-to-stage').val();

        let data = {
            "idStage": stage,
            "comment": comment,
            "idMain": idMain,
            "id": idCommentToStage
        }

        $.ajax({
            url: '/update/save-comment-to-stage',
            method: 'put',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function (data) {
                if (data !== null) {
                    idCommentToStage = data;
                    alert("Комментарий успешно обновленны !!!");
                } else {
                    alert("Возникла ошибка при сохранении комментария !!!");
                }
            },
            beforeSend: function () {
            },
            complete: function () {
            },
            error: function (xhr, status, error) {
            }
        });
    })

    $('#stage').change(function () {
        let stage = $('#stage').val();

        let data = {
            "idStage": stage,
            "idMain": idMain,
        }

        $.ajax({
            url: '/search/get-comment-to-stage',
            method: 'post',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function (data) {
                if (data) {
                    idCommentToStage = data.id;
                    $('#comment-to-stage').val(data.comment);
                }
            },
            beforeSend: function () {
            },
            complete: function () {
            },
            error: function (xhr, status, error) {
            }
        });


    })

})