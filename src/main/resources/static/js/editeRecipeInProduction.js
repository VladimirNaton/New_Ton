$(document).ready(function () {

    let selectedComponents = '1';
    let columnVisible = false;
    let findComponent = '';
    let codeSelectedElemRecipeTable;
    let idSelectedElemRecipeTable = '';
    let idSelectedElemComponentTable = '';
    let idMain = '';
    let nameComponent = '';
    let sequenceNumberFirstValue = '';
    let errorMessageShow = false;
    let outPast = false;
    let idCommentToStage = '';

    idMain = $('#id-edite-recipe').text();
    nameComponent = $("#component-select option:selected").text();


    let editeRecipeTable = $('#edite-recipe-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "paging": false,
        "info": false,
        "ajax": {
            "url": "/search/data-for-edite-recipe-table",
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
            }
            ,
            {
                "targets": 10,
                "orderable": false
            }
            ,
            {
                "targets": 11,
                "orderable": false
            }
            ,
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
                url: '/search/get-data-for-head-string-edite-recipe/' + idProd,
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

    let componentTable = $('#component-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "ajax": {
            "url": "/search/data-for-edite-recipe-component-table",
            "type": "POST",
            data: function (data) {
                data.codeSearch = selectedComponents;
                data.findComponent = findComponent;
            }
        },
        "lengthMenu": [[10, 15, 20], [10, 15, 20]],
        "columns": [
            {"data": "id"},
            {"data": "dateStr"},
            {"data": "nameraw"}
        ],
        "columnDefs": [
            {
                "targets": 0,
                "orderable": false,
                "visible": false
            },
            {
                "targets": 1,
                "orderable": false,
                "visible": false
            }
            ,
            {
                "targets": 2,
                "orderable": false
            }
        ]
    });

    $('#component-select').change(function () {
        selectedComponents = $('#component-select').find('option:selected').val();
        nameComponent = $("#component-select option:selected").text();
        idSelectedElemComponentTable = '';
        switch (selectedComponents) {
            case '1':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                outPast = false;
                $('#container-past-edite').hide();
                break;
            case '2':
                if (!columnVisible) {
                    showFirstColumn();
                    columnVisible = true;
                }
                outPast = false;
                $('#container-past-edite').show();
                break;
            case '3':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                outPast = false;
                $('#container-past-edite').hide();
                break;
            case '4':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                outPast = false;
                $('#container-past-edite').hide();
                break;
            case '5':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                outPast = false;
                $('#container-past-edite').hide();
                break;
            case '6':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                outPast = false;
                $('#container-past-edite').hide();
                break;
            case '7':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                outPast = false;
                $('#container-past-edite').hide();
                break;
            case '8':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                outPast = false;
                $('#container-past-edite').hide();
                break;
            case '9':
                if (!columnVisible) {
                    showFirstColumn();
                    columnVisible = true;
                }
                outPast = true;
                $('#container-past-edite').show();
                break;
        }
        $('#find-component-input').val('');
        findComponent = '';
        componentTable.ajax.reload();
    })


    $('#component-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            idSelectedElemComponentTable = '';
        } else {
            componentTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            idSelectedElemComponentTable = componentTable.row(this).data().id;
        }
    });

    function showFirstColumn() {
        componentTable.columns([1]).visible(true);
    }

    function hideFirstColumn() {
        componentTable.columns([1]).visible(false);
    }

    $('#find-component').click(function () {
        findComponent = $('#find-component-input').val();
        componentTable.ajax.reload();
    })

    $('#reset-find-component').click(function () {
        findComponent = '';
        $('#find-component-input').val('');
        componentTable.ajax.reload();
    })

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

    $('#remove-component-from-recipe').click(function () {
        if (idSelectedElemRecipeTable !== '') {
            $.ajax({
                url: '/update/delete-selected-row-from-recipe-table/' + idSelectedElemRecipeTable,
                method: 'delete',
                success: function (data) {
                    if (data) {
                        editeRecipeTable.ajax.reload();
                        idSelectedElemRecipeTable = '';
                        clearInputData();
                        hideAllParameter();
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

    function getDataForSelectedRowEditeRecipeTable(id) {
        $.ajax({
            url: '/search/get-data-for-selected-row-edite-recipe-table/' + id,
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
        if (data.componentLoaded === 0) {
            removePropertyReadOnlyCode1();
        } else {
            addPropertyReadOnlyCode1();
        }
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
        if (data.componentLoaded === 0) {
            removePropertyReadOnlyCode2();
        } else {
            addPropertyReadOnlyCode2();
        }
    }

    function compliteSolventRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#mass-input').val(data.mass);
        if (data.componentLoaded === 0) {
            removePropertyReadOnlyCode6();
        } else {
            addPropertyReadOnlyCode6();
        }
    }

    function compliteMixingRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#mixing-input').val(data.turnmix);
        $('#mixing-time-input').val(data.timemix);
        if (data.componentLoaded === 0) {
            removePropertyReadOnlyCode8();
        } else {
            addPropertyReadOnlyCode8();
        }
    }

    function compliteFilterRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        $('#filter-input').val(data.filter);
        if (data.componentLoaded === 0) {
            removePropertyReadOnlyCode4();
        } else {
            addPropertyReadOnlyCode4();
        }
    }

    function compliteAnotherRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
        if (data.componentLoaded === 0) {
            removePropertyReadOnlyAnotherCode();
        } else {
            addPropertyReadOnlyAnotherCode();
        }
    }

    $('#percent-input').change(function () {
        let percent = Number($('#percent-input').val());
        if (percent !== 0) {
            let result = calculateMass(percent);
            $('#mass-input').val(result.toFixed(2));
            checkMassAndInfelicityMass();
            checkCommonPercents();
        }
    })

    $('#mass-input').change(function () {
        let mass = Number($('#mass-input').val());
        if (mass !== 0 && $("#percent").is(":visible")) {
            let result = calculatePercent(mass);
            $('#percent-input').val(result.toFixed(2));
            checkMassAndInfelicityMass();
            checkCommonPercents();
        }
    })

    $('#infelicity-percent-input').change(function () {
        let percent = Number($('#infelicity-percent-input').val());
        if (percent !== 0) {
            let result = calculateInfelicityMass(percent);
            $('#infelicity-mass-input').val(result.toFixed(2));
            checkMassAndInfelicityMass();
            checkCommonPercents();
        }
    })

    $('#infelicity-mass-input').change(function () {
        let mass = Number($('#infelicity-mass-input').val());
        if (mass !== 0) {
            let result = calculateInfelicityPercent(mass);
            $('#infelicity-percent-input').val(result.toFixed(2));
            checkMassAndInfelicityMass();
            checkCommonPercents();
        }
    })

    $('#stage-input').change(function () {
        let stage = Number($('#stage-input').val());
        if (stage !== 0) {
            $('#error-message').hide();
        }
        sequenceNumberError();
    })


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

    function calculatePercent(mass) {
        let commonWeight = Number($('#common-weight-edite-recipe').val());
        let result = mass / (commonWeight / 100);
        return result;
    }

    function calculateInfelicityMass(percent) {
        let commonWeight = Number($('#common-weight-edite-recipe').val());
        let result = (commonWeight / 100) * percent;
        return result;
    }

    function calculateInfelicityPercent(mass) {
        let commonWeight = Number($('#common-weight-edite-recipe').val());
        let result = mass / (commonWeight / 100);
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

    $('#add-component').click(function () {
        if (idSelectedElemComponentTable !== '' && idMain !== '') {
            addNewComponentToRecipe(idSelectedElemComponentTable, idMain, '', '')
            clearInputData();
            hideAllParameter();
        } else {
            if (selectedComponents > 2 && selectedComponents < 9) {
                addNewComponentToRecipe(idSelectedElemComponentTable, idMain, nameComponent, selectedComponents);
                clearInputData();
                hideAllParameter();
            } else {
                alert("Вы не выбрали ни одной записи !!!");
            }
        }
    })

    function addNewComponentToRecipe(idSelectedElemComponentTable, idMain, nameComponent, code) {
        let data = {
            "idComponentTable": idSelectedElemComponentTable,
            "idMain": idMain,
            "nameSelectedComponent": nameComponent,
            "code": code,
            "outPast": outPast
        }
        $.ajax({
            url: '/update/add-component-to-recipe',
            method: 'post',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function (data) {
                if (data) {
                    editeRecipeTable.ajax.reload(checkCommonPercents());
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

    $('#replace-component').click(function () {
        if (idSelectedElemRecipeTable !== '' && idSelectedElemComponentTable !== '' || idSelectedElemRecipeTable !== '' && (selectedComponents > 2 && selectedComponents < 9)) {
            replaceAjax();
        } else {
            alert("Вы не выбрали ни одной записи или не выбран элемент в одной из таблиц !!!");
        }
    })

    function replaceAjax() {
        let data = {
            "idComponentTable": idSelectedElemComponentTable,
            "idMain": idMain,
            "nameSelectedComponent": nameComponent,
            "code": selectedComponents,
            "idSelectedElemRecipeTable": idSelectedElemRecipeTable,
            "outPast": outPast
        }
        $.ajax({
            url: '/update/replace-selected-recipe-element',
            method: 'put',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function (data) {
                if (data) {
                    idSelectedElemRecipeTable = '';
                    editeRecipeTable.ajax.reload(checkCommonPercents());
                    clearInputData();
                    hideAllParameter();
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

    $('#save-change').click(function () {
        if (idSelectedElemRecipeTable !== '') {
            let sequenceNumber = $('#sequence-number-input').val();
            let stage = $('#stage-input').val();
            let percent = $('#percent-input').val();
            let mass = $('#mass-input').val();
            let mixing = $('#mixing-input').val();
            let mixingTime = $('#mixing-time-input').val();
            let filter = $('#filter-input').val();
            let infelicityPercent = $('#infelicity-percent-input').val();
            let infelicityMass = $('#infelicity-mass-input').val();
            let part = $('#part').val();
            let pastDate = $('#past-date').val();

            if (stage === '0') {
                errorStageMessage();
            } else {
                $('#error-message').hide();
            }
            if (stage !== '0' && !errorMessageShow) {
                let data = {
                    "sequenceNumber": sequenceNumber,
                    "stage": stage,
                    "percent": percent,
                    "mass": mass,
                    "mixing": mixing,
                    "mixingTime": mixingTime,
                    "filter": filter,
                    "infelicityPercent": infelicityPercent,
                    "infelicityMass": infelicityMass,
                    "part": part,
                    "pastDate": pastDate,
                    "selectedComponentId": idSelectedElemRecipeTable
                }
                $.ajax({
                    url: '/update/selected-row-of-recipe',
                    method: 'put',
                    contentType: 'application/json;charset=utf-8',
                    data: JSON.stringify(data),
                    success: function (data) {
                        if (data) {
                            editeRecipeTable.ajax.reload();
                            alert("Данные успешно обновленны !!!");
                            idSelectedElemRecipeTable = '';
                            clearInputData();
                            hideAllParameter();
                        }
                    },
                    beforeSend: function () {
                    },
                    complete: function () {
                    },
                    error: function (xhr, status, error) {
                        alert("Возникла ошибка при обновлении данных !!!");
                    }
                });
            }
        }
    })

    function clearInputData() {
        $('#sequence-number-input').val('');
        $('#stage-input').val('');
        $('#percent-input').val('');
        $('#mass-input').val('');
        $('#mixing-input').val('');
        $('#mixing-time-input').val('');
        $('#filter-input').val('');
        $('#infelicity-percent-input').val('');
        $('#infelicity-mass-input').val('');
        $('#part').val('');
        $('#past-date').val('');
        $('#name-prod-input').val('');
        $('#mass-input-product-in-production').val('');
        $('#infelicity-mass-input-product-in-production').val('');
    }

    $('#save-recipe').click(function () {
        let comment = $('#comment-technologist').val();
        let tempMin = $('#temp-min').val();
        let tempMax = $('#temp-max').val();
        let commonWeight = $('#common-weight-edite-recipe').val();
        let idMain = $('#id-edite-recipe').text();
        let control = $('#control').val();
        let data = {
            "comment": comment,
            "tempMin": tempMin,
            "tempMax": tempMax,
            "commonWeight": commonWeight,
            "idMain": idMain,
            "control": control
        }
        $.ajax({
            url: '/update/save-recipe',
            method: 'put',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function (data) {
                if (data) {
                    alert("Данные успешно обновленны !!!");
                } else {
                    alert("Возникла ошибка при обновлении данных !!!");
                }
            },
            beforeSend: function () {
            },
            complete: function () {
            },
            error: function (xhr, status, error) {
                alert("Возникла ошибка при обновлении данных !!!");
            }
        });
    })

    function checkSequenceNumber(data, sequenceNumber) {
        let present = false;
        $.each(data, function (key, value) {
            if (Number(sequenceNumber) === Number(value.n)) {
                present = true;
            }
        });
        return present;

    }

    $('#sequence-number-input').change(function () {
        sequenceNumberError();
    });

    function sequenceNumberError() {
        let dataTable = getTableData();
        let sequenceNumber = $('#sequence-number-input').val();

        let present = checkSequenceNumber(dataTable, sequenceNumber);
        if (present) {
            errorSequenceNumberMessage();
            errorMessageShow = true;
        }
        if (!present || sequenceNumber === sequenceNumberFirstValue) {
            $('#error-message').hide();
            errorMessageShow = false;
        }
    }

    function errorSequenceNumberMessage() {
        $('#error-message').text("Такой номер по порядку уже присутствует !!!");
        $('#error-message').show();

    }

    function errorStageMessage() {
        $('#error-message').text("Этап не может быть равен 0  !!!");
        $('#error-message').show();
    }

    function addPropertyReadOnlyCode1() {
        $('#sequence-number-input').attr('readonly', true);
        $('#stage-input').attr('readonly', true);
        $('#percent-input').attr('readonly', true);
        $('#mass-input').attr('readonly', true);
        $('#infelicity-percent-input').attr('readonly', true);
        $('#infelicity-mass-input').attr('readonly', true);
    }

    function removePropertyReadOnlyCode1() {
        $('#sequence-number-input').attr('readonly', false);
        $('#stage-input').attr('readonly', false);
        $('#percent-input').attr('readonly', false);
        $('#mass-input').attr('readonly', false);
        $('#infelicity-percent-input').attr('readonly', false);
        $('#infelicity-mass-input').attr('readonly', false);
    }

    function addPropertyReadOnlyCode2() {
        $('#sequence-number-input').attr('readonly', true);
        $('#stage-input').attr('readonly', true);
        $('#percent-input').attr('readonly', true);
        $('#mass-input').attr('readonly', true);
        $('#infelicity-percent-input').attr('readonly', true);
        $('#infelicity-mass-input').attr('readonly', true);
        $('#part').attr('readonly', true);
        $('#past-date').attr('readonly', true);
    }

    function removePropertyReadOnlyCode2() {
        $('#sequence-number-input').attr('readonly', false);
        $('#stage-input').attr('readonly', false);
        $('#percent-input').attr('readonly', false);
        $('#mass-input').attr('readonly', false);
        $('#infelicity-percent-input').attr('readonly', false);
        $('#infelicity-mass-input').attr('readonly', false);
        $('#part').attr('readonly', false);
        $('#past-date').attr('readonly', false);
    }

    function addPropertyReadOnlyCode6() {
        $('#sequence-number-input').attr('readonly', true);
        $('#stage-input').attr('readonly', true);
        $('#mass-input').attr('readonly', true);
    }

    function removePropertyReadOnlyCode6() {
        $('#sequence-number-input').attr('readonly', false);
        $('#stage-input').attr('readonly', false);
        $('#mass-input').attr('readonly', false);
    }

    function addPropertyReadOnlyCode4() {
        $('#sequence-number-input').attr('readonly', true);
        $('#stage-input').attr('readonly', true);
        $('#filter-input').attr('readonly', true);
    }

    function removePropertyReadOnlyCode4() {
        $('#sequence-number-input').attr('readonly', false);
        $('#stage-input').attr('readonly', false);
        $('#filter-input').attr('readonly', false);
    }

    function addPropertyReadOnlyCode8() {
        $('#sequence-number-input').attr('readonly', true);
        $('#stage-input').attr('readonly', true);
        $('#mixing-input').attr('readonly', true);
        $('#mixing-time-input').attr('readonly', true);
    }

    function removePropertyReadOnlyCode8() {
        $('#sequence-number-input').attr('readonly', false);
        $('#stage-input').attr('readonly', false);
        $('#mixing-input').attr('readonly', true);
        $('#mixing-time-input').attr('readonly', true);
    }

    function addPropertyReadOnlyAnotherCode() {
        $('#sequence-number-input').attr('readonly', true);
        $('#stage-input').attr('readonly', true);
    }

    function removePropertyReadOnlyAnotherCode() {
        $('#sequence-number-input').attr('readonly', false);
        $('#stage-input').attr('readonly', false);
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