$(document).ready(function () {

    let selectedComponents = '1';
    let columnVisible = false;
    let findComponent = '';
    let codeSelectedElemRecipeTable;
    let idSelectedElemRecipeTable = '';


    let editeRecipeTable = $('#edite-recipe-table').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "lengthChange": false,
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
            {"data": "devmass"}
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
        ]
    });


    $('#edite-recipe-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            let elem = $(this);
            codeSelectedElemRecipeTable = elem[0].cells[2].innerText;
            showRecipeComponentInformation(codeSelectedElemRecipeTable);
            idSelectedElemRecipeTable = editeRecipeTable.row(this).data().id;
            getDataForSelectedRowEditeRecipeTable(idSelectedElemRecipeTable);
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
                    $('#control').val(data.percent);
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
        switch (selectedComponents) {
            case '1':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '2':
                if (!columnVisible) {
                    showFirstColumn();
                    columnVisible = true;
                }
                $('#container-past-edite').show();
                break;
            case '3':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '4':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '5':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '6':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '7':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
            case '8':
                if (columnVisible) {
                    hideFirstColumn();
                    columnVisible = false;
                }
                $('#container-past-edite').hide();
                break;
        }
        $('#find-component-input').val('');
        findComponent = '';
        componentTable.ajax.reload();
    })


    $('#component-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            let elem = $(this);
            let code = elem[0].cells[0].innerText;
            let id = componentTable.row(this).data().id;
        } else {
            componentTable.$('tr.selected').removeClass('selected');
            let id = componentTable.row(this).data().id;
            $(this).addClass('selected');
            let elem = $(this);
            let code = elem[0].cells[0].innerText;
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
    }

    function showCodeParameterPast() {
        $('#percent').show();
        $('#mass').show();
        $('#infelicity-percent').show();
        $('#infelicity-mass').show();
        $('#part-label').show();
        $('#part-date-label').show();
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
    }

    function showFilterParameter() {
        $('#filter').show();
    }

    function showSolventParameter() {
        $('#mass').show();
    }

    function showMixParameter() {
        $('#mixing').show();
        $('#mixing-time').show();
    }

    $('#remove-component-from-recipe').click(function () {
        if (idSelectedElemRecipeTable !== '') {
            $.ajax({
                url: '/update/delete-selected-row-from-recipe-table/' + idSelectedElemRecipeTable,
                method: 'delete',
                success: function (data) {
                    if (data) {
                        editeRecipeTable.ajax.reload();
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
        $('#filter-input').val(data.filter);
    }

    function compliteAnotherRowInformation(data) {
        $('#sequence-number-input').val(data.n);
        $('#stage-input').val(data.stage);
        $('#name-prod-input').val(data.nameraw);
    }

})