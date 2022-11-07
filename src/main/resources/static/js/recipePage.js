$(document).ready(function () {

    let idSelected = $('#id').html();

    $.ajax({
        url: '/api/v1/recipePageData?id=' + idSelected,
        method: 'post',
        success: function (data) {
            console.log(data)

            let sumWeightDto = data.sumWeightDto;
            if (sumWeightDto.barrel1 !== null) {
                $('#b1').text(sumWeightDto.barrel1);
            }
            if (sumWeightDto.barrel2 !== null) {
                $('#b2').text(sumWeightDto.barrel2);
            }
            if (sumWeightDto.barrel3 !== null) {
                $('#b3').text(sumWeightDto.barrel3);
            }
            if (sumWeightDto.barrel4 !== null) {
                $('#b4').text(sumWeightDto.barrel4);
            }
            if (sumWeightDto.sum !== null) {
                $('#sum').text(sumWeightDto.sum);
            }

            let dto = data.recipeMainTableDtoList;

            let arrTr = [];

            let first = false;

            let allMassSum = 0;
            let allMassFact = 0;
            let allTimeMade = 0;
            let timeMixed = 0;
            let timeMixedFact = 0;
            let factPercent = 0;


            $.each(dto, function (index, value) {
                if (index === 0) {
                    allMassSum += parseFloat(value.mass);
                    allMassFact +=  parseFloat(value.factmass);
                    allTimeMade +=  parseFloat(value.timemade);
                    timeMixed += parseFloat(data.code8Stage1.timemix);
                    timeMixedFact += parseFloat(data.code8Stage1.facttimemix);
                    factPercent += parseFloat(value.percent);
                    let tr = document.createElement("tr");
                    let td1 = document.createElement("td")
                    td1.rowSpan = data.collSpanStage1;
                    td1.className = 'borderTd';
                    td1.innerText = '1';
                    tr.append(td1);
                    let td2 = document.createElement("td")
                    td2.className = 'borderTd';
                    td2.id = '2';
                    td2.innerText = value.nameraw;
                    tr.append(td2)
                    let td3 = document.createElement("td")
                    td3.className = 'borderTd';
                    td3.innerText = value.percent
                    tr.append(td3)
                    let td4 = document.createElement("td")
                    td4.className = 'borderTd';
                    td4.innerText = value.mass;
                    tr.append(td4)
                    let td5 = document.createElement("td")
                    td5.className = 'borderTd';
                    td5.innerText = value.factmass
                    tr.append(td5)
                    let td6 = document.createElement("td")
                    td6.className = 'borderTd';
                    td6.innerText = value.devmass;
                    tr.append(td6)
                    let td7 = document.createElement("td")
                    td7.className = 'borderTd';
                    td7.innerText = value.devper;
                    tr.append(td7)
                    let td8 = document.createElement("td")
                    td8.className = 'borderTd';
                    td8.innerText = value.factmassdev;
                    tr.append(td8)
                    let td9 = document.createElement("td")
                    td9.className = 'borderTd';
                    td9.innerText = value.timemade;
                    tr.append(td9)
                    let td10 = document.createElement("td")
                    td10.innerText = data.code8Stage1.turnmix;
                    td10.className = 'borderTd';
                    td10.rowSpan = data.collSpanStage1;
                    tr.append(td10)
                    let td11 = document.createElement("td")
                    td11.className = 'borderTd';
                    td11.innerText = data.code8Stage1.devturn;
                    td11.rowSpan = data.collSpanStage1;
                    tr.append(td11)
                    let td12 = document.createElement("td")
                    td12.className = 'borderTd';
                    td12.innerText = data.code8Stage1.factturn;
                    td12.rowSpan = data.collSpanStage1;
                    tr.append(td12)
                    let td13 = document.createElement("td")
                    td13.className = 'borderTd';
                    td13.innerText = data.code8Stage1.timemix;
                    td13.rowSpan = data.collSpanStage1;
                    tr.append(td13)
                    let td14 = document.createElement("td")
                    td14.className = 'borderTd';
                    td14.innerText = data.code8Stage1.facttimemix;
                    td14.rowSpan = data.collSpanStage1;
                    tr.append(td14)
                    let td15 = document.createElement("td")
                    td15.className = 'borderTd';
                    td15.innerText = value.tempdep;
                    tr.append(td15)
                    let td16 = document.createElement("td")
                    td16.innerText = value.datestart;
                    td16.className = 'borderTd';
                    tr.append(td16)
                    let td17 = document.createElement("td")
                    td17.innerText = value.datestop;
                    td17.className = 'borderTd';
                    tr.append(td17)
                    let td18 = document.createElement("td")
                    td18.className = 'borderTd';
                    td18.innerText = value.prodtemp;
                    tr.append(td18)
                    let td19 = document.createElement("td")
                    td19.className = 'borderTd';
                    td19.innerText = value.wetdep;
                    tr.append(td19)
                    let td20 = document.createElement("td")
                    td20.className = 'borderTd';
                    td20.innerText = value.pastdate;
                    tr.append(td20)
                    arrTr.unshift(tr);
                }
                if (index > 0 && index && value.stage === 1) {
                    allMassSum += parseFloat(value.mass);
                    allMassFact +=  parseFloat(value.factmass);
                    allTimeMade +=  parseFloat(value.timemade);
                    factPercent += parseFloat(value.percent);
                    let tr = document.createElement("tr");
                    let td1 = document.createElement("td")
                    td1.className = 'borderTd';
                    td1.innerText = value.nameraw;
                    tr.append(td1);
                    let td2 = document.createElement("td")
                    td2.className = 'borderTd';
                    td2.innerText = value.percent
                    tr.append(td2)
                    let td3 = document.createElement("td")
                    td3.className = 'borderTd';
                    td3.innerText = value.mass
                    tr.append(td3)
                    let td4 = document.createElement("td")
                    td4.className = 'borderTd';
                    td4.innerText = value.factmass
                    tr.append(td4)
                    let td5 = document.createElement("td")
                    td5.className = 'borderTd';
                    td5.innerText = value.devmass
                    tr.append(td5)
                    let td6 = document.createElement("td")
                    td6.className = 'borderTd';
                    td6.innerText = value.devper;
                    tr.append(td6)
                    let td7 = document.createElement("td")
                    td7.className = 'borderTd';
                    td7.innerText = value.factmassdev;
                    tr.append(td7)
                    let td8 = document.createElement("td")
                    td8.className = 'borderTd';
                    td8.innerText = value.timemade;
                    tr.append(td8)
                    let td9 = document.createElement("td")
                    td9.className = 'borderTd';
                    td9.innerText = value.tempdep;
                    tr.append(td9)
                    let td10 = document.createElement("td")
                    td10.className = 'borderTd';
                    td10.innerText = value.datestart;
                    tr.append(td10)
                    let td11 = document.createElement("td")
                    td11.className = 'borderTd';
                    td11.innerText = value.datestop;
                    tr.append(td11)
                    let td12 = document.createElement("td")
                    td12.className = 'borderTd';
                    td12.innerText = value.prodtemp;
                    tr.append(td12)
                    let td13 = document.createElement("td")
                    td13.className = 'borderTd';
                    td13.innerText = value.wetdep;
                    tr.append(td13)
                    let td14 = document.createElement("td")
                    td14.className = 'borderTd';
                    td14.innerText = value.pastdate;
                    tr.append(td14)
                    arrTr.unshift(tr);
                }

                if (value.stage === 2 && first) {
                    allMassSum += parseFloat(value.mass);
                    allMassFact +=  parseFloat(value.factmass);
                    allTimeMade +=  parseFloat(value.timemade);
                    factPercent += parseFloat(value.percent);
                    let tr = document.createElement("tr");
                    let td1 = document.createElement("td")
                    td1.className = 'borderTd';
                    td1.innerText = value.nameraw;
                    tr.append(td1);
                    let td2 = document.createElement("td")
                    td2.className = 'borderTd';
                    td2.innerText = value.percent;
                    tr.append(td2)
                    let td3 = document.createElement("td")
                    td3.className = 'borderTd';
                    td3.innerText = value.mass
                    tr.append(td3)
                    let td4 = document.createElement("td")
                    td4.className = 'borderTd';
                    td4.innerText = value.factmass
                    tr.append(td4)
                    let td5 = document.createElement("td")
                    td5.className = 'borderTd';
                    td5.innerText = value.devmass
                    tr.append(td5)
                    let td6 = document.createElement("td")
                    td6.className = 'borderTd';
                    td6.innerText = value.devper;
                    tr.append(td6)
                    let td7 = document.createElement("td")
                    td7.className = 'borderTd';
                    td7.innerText = value.factmassdev;
                    tr.append(td7)
                    let td8 = document.createElement("td")
                    td8.className = 'borderTd';
                    td8.innerText = value.timemade;
                    tr.append(td8)
                    let td9 = document.createElement("td")
                    td9.innerText = value.tempdep;
                    td9.className = 'borderTd';
                    tr.append(td9)
                    let td10 = document.createElement("td")
                    td10.className = 'borderTd';
                    td10.innerText = value.datestart;
                    tr.append(td10)
                    let td11 = document.createElement("td")
                    td11.className = 'borderTd';
                    td11.innerText = value.datestop;
                    tr.append(td11)
                    let td12 = document.createElement("td")
                    td12.className = 'borderTd';
                    td12.innerText = value.prodtemp;
                    tr.append(td12)
                    let td13 = document.createElement("td")
                    td13.className = 'borderTd';
                    td13.innerText = value.wetdep;
                    tr.append(td13)
                    let td14 = document.createElement("td")
                    td14.className = 'borderTd';
                    td14.innerText = value.pastdate;
                    tr.append(td14)
                    arrTr.unshift(tr);
                }

                if (value.stage === 2 && !first) {
                    allMassSum += parseFloat(value.mass);
                    allMassFact +=  parseFloat(value.factmass);
                    allTimeMade +=  parseFloat(value.timemade);
                    timeMixed += parseFloat(data.code8Stage2.timemix);
                    timeMixedFact += parseFloat(data.code8Stage2.facttimemix);
                    factPercent += parseFloat(value.percent);
                    first = true;
                    let tr = document.createElement("tr");
                    let td1 = document.createElement("td")
                    td1.rowSpan = data.collSpanStage2;
                    td1.className = 'borderTd';
                    td1.innerText = '2';
                    tr.append(td1);
                    let td2 = document.createElement("td")
                    td2.className = 'borderTd';
                    td2.innerText = value.nameraw;
                    tr.append(td2)
                    let td3 = document.createElement("td")
                    td3.className = 'borderTd';
                    td3.innerText = value.percent;
                    tr.append(td3)
                    let td4 = document.createElement("td")
                    td4.className = 'borderTd';
                    td4.innerText = value.mass
                    tr.append(td4)
                    let td5 = document.createElement("td")
                    td5.className = 'borderTd';
                    td5.innerText = value.factmass
                    tr.append(td5)
                    let td6 = document.createElement("td")
                    td6.className = 'borderTd';
                    td6.innerText = value.devmass
                    tr.append(td6)
                    let td7 = document.createElement("td")
                    td7.className = 'borderTd';
                    td7.innerText = value.devper;
                    tr.append(td7)
                    let td8 = document.createElement("td")
                    td8.className = 'borderTd';
                    td8.innerText = value.factmassdev;
                    tr.append(td8)
                    let td9 = document.createElement("td")
                    td9.className = 'borderTd';
                    td9.innerText = value.timemade;
                    tr.append(td9)
                    let td10 = document.createElement("td")
                    td10.className = 'borderTd';
                    td10.innerText = data.code8Stage2.turnmix;
                    td10.rowSpan = data.collSpanStage2;
                    tr.append(td10)
                    let td11 = document.createElement("td")
                    td11.className = 'borderTd';
                    td11.rowSpan = data.collSpanStage2;
                    td11.innerText = data.code8Stage2.devturn
                    tr.append(td11)
                    let td12 = document.createElement("td")
                    td12.className = 'borderTd';
                    td12.rowSpan = data.collSpanStage2;
                    td12.innerText = data.code8Stage2.factturn;
                    tr.append(td12)
                    let td13 = document.createElement("td")
                    td13.className = 'borderTd';
                    td13.innerText = data.code8Stage2.timemix;
                    td13.rowSpan = data.collSpanStage2;
                    tr.append(td13)
                    let td14 = document.createElement("td")
                    td14.className = 'borderTd';
                    td14.innerText = data.code8Stage2.facttimemix;
                    td14.rowSpan = data.collSpanStage2;
                    tr.append(td14)
                    let td15 = document.createElement("td")
                    td15.className = 'borderTd';
                    td15.innerText = value.tempdep;
                    tr.append(td15)
                    let td16 = document.createElement("td")
                    td16.className = 'borderTd';
                    td16.innerText = value.datestart;
                    tr.append(td16)
                    let td17 = document.createElement("td")
                    td17.className = 'borderTd';
                    td17.innerText = value.datestop;
                    tr.append(td17)
                    let td18 = document.createElement("td")
                    td18.className = 'borderTd';
                    td18.innerText = value.prodtemp;
                    tr.append(td18)
                    let td19 = document.createElement("td")
                    td19.className = 'borderTd';
                    td19.innerText = value.wetdep;
                    tr.append(td19)
                    let td20 = document.createElement("td")
                    td20.className = 'borderTd';
                    td20.innerText = value.pastdate;
                    tr.append(td20)
                    arrTr.unshift(tr);
                }

                if (value.stage === 3) {
                    allMassSum += parseFloat(value.mass);
                    allMassFact +=  parseFloat(value.factmass);
                    allTimeMade +=  parseFloat(value.timemade);
                    timeMixed += parseFloat(value.timemix);
                    timeMixedFact += parseFloat(value.facttimemix);
                    factPercent += parseFloat(value.percent);
                    let tr = document.createElement("tr");
                    let td1 = document.createElement("td")
                    td1.rowSpan = data.collSpanStage3;
                    td1.className = 'borderTd';
                    td1.innerText = '3';
                    tr.append(td1);
                    let td2 = document.createElement("td")
                    td2.className = 'borderTd';
                    td2.innerText = value.nameraw;
                    tr.append(td2)
                    let td3 = document.createElement("td")
                    td3.className = 'borderTd';
                    td3.innerText = value.percent;
                    tr.append(td3)
                    let td4 = document.createElement("td")
                    td4.innerText = value.mass
                    td4.className = 'borderTd';
                    tr.append(td4)
                    let td5 = document.createElement("td")
                    td5.className = 'borderTd';
                    td5.innerText = value.factmass
                    tr.append(td5)
                    let td6 = document.createElement("td")
                    td6.className = 'borderTd';
                    td6.innerText = value.devmass
                    tr.append(td6)
                    let td7 = document.createElement("td")
                    td7.className = 'borderTd';
                    td7.innerText = value.devper;
                    tr.append(td7)
                    let td8 = document.createElement("td")
                    td8.className = 'borderTd';
                    td8.innerText = value.factmassdev;
                    tr.append(td8)
                    let td9 = document.createElement("td")
                    td9.className = 'borderTd';
                    td9.innerText = value.timemade;
                    tr.append(td9)
                    let td10 = document.createElement("td")
                    td10.className = 'borderTd';
                    td10.innerText = value.turnmix;
                    tr.append(td10)
                    let td11 = document.createElement("td")
                    td11.className = 'borderTd';
                    td11.innerText = value.devturn
                    tr.append(td11)
                    let td12 = document.createElement("td")
                    td12.className = 'borderTd';
                    td12.innerText = value.factturn;
                    tr.append(td12)
                    let td13 = document.createElement("td")
                    td13.className = 'borderTd';
                    td13.innerText = value.timemix;
                    tr.append(td13)
                    let td14 = document.createElement("td")
                    td14.className = 'borderTd';
                    td14.innerText = value.facttimemix;
                    tr.append(td14)
                    let td15 = document.createElement("td")
                    td15.className = 'borderTd';
                    td15.innerText = value.tempdep;
                    tr.append(td15)
                    let td16 = document.createElement("td")
                    td16.className = 'borderTd';
                    td16.innerText = value.datestart;
                    tr.append(td16)
                    let td17 = document.createElement("td")
                    td17.className = 'borderTd';
                    td17.innerText = value.datestop;
                    tr.append(td17)
                    let td18 = document.createElement("td")
                    td18.className = 'borderTd';
                    td18.innerText = value.prodtemp;
                    tr.append(td18)
                    let td19 = document.createElement("td")
                    td19.className = 'borderTd';
                    td19.innerText = value.wetdep;
                    tr.append(td19)
                    let td20 = document.createElement("td")
                    td20.className = 'borderTd';
                    td20.innerText = value.pastdate;
                    tr.append(td20)
                    arrTr.unshift(tr);
                }

                let tbody = document.getElementById("mainTableTBody");
                $.each(arrTr, function (index, value) {
                    tbody.prepend(value);
                })

                $('#all-mass-sum').text(allMassSum);
                $('#all-mass-fact').text(allMassFact);
                $('#all-time-made').text(allTimeMade);
                $('#time-mixed').text(timeMixed);
                $('#time-mixed-fact').text(timeMixedFact);
                $('#time').text(data.allTimeMade);
                $('#name-prod').text(data.nameProd);
                $('#mass').text(data.mainMass);
                $('#fact-percent').text(factPercent);
                $('#number').text(data.mainMass);
                $('#deg').text(data.deg);
                $('#right8').text(data.dateMade);
                $('#right4').text(data.currentDate);
                $('#right6').text(data.currentDate);
                $('#date').text(data.currentDate);

            })


        },
        beforeSend: function () {
        },
        complete: function () {
        },
        error: function (xhr, status, error) {
            alert("Возникла ошибка при отправке данных !!!");
        }
    });


})
