const navItems = document.querySelectorAll(".nav-item");
const filterForm = document.querySelector(".filter");
const chart = document.querySelector("#chart");

$(document).ready(function () {
    GetStatistic()
});

navItems.forEach(item => {
    item.addEventListener("click", e => {
        ResetNavItem();
        item.classList.toggle("active");
        GetStatistic();
    });
});

function ResetNavItem() {
    navItems.forEach(item => {
        item.classList.remove("active");
    });
}

//var types = new Map();
//types.set(1, "Monthly Revenue");
//types.set(2, "Daily Revenue");
//types.set(3, "Monthly Orders");
//types.set(4, "Daily Orders");

function getStaisticType() {
    let type = "Monthly Revenue";
    navItems.forEach(item => {
        if (item.classList.contains("active")) {
            type = item.innerText.trim();
        }
    });
    return type;
}

function getStaisticUnit() {
    let unit = "Dollars";
    navItems.forEach(item => {
        if (item.classList.contains("active")) {
            unit = item.innerText.trim().includes("Orders") ? "Order" : "Dollars";
        }
    });
    return unit;
}


function GetStatistic() {
    var from = document.querySelector("#from").value;
    var to = document.querySelector("#to").value;
    const type = getStaisticType();
    if (type.includes("Daily") && new Date(from).getMonth() !== new Date(to).getMonth()) {
        alert("Please choose the same month!");
        return;
    }

    console.log(from, to, type);

    $.ajax({
        url: "admin",
        data: {
            type: type,
            from: from,
            to: to
        },
        success: function (dataPoints) {
            if (dataPoints !== null) {
                if (type.includes("category")) {
                    var chart = new CanvasJS.Chart("chart", {
                        height: 550,
                        exportEnabled: true,
                        exportFileName: type,
                        animationEnabled: true,
                        animationDuration: 1000,
                                title: {
                                    text: type
                                },
                        legend: {
                            cursor: "pointer",
                            itemclick: explodePie
                        },
                        data: [{
                                type: "pie",
                                percentFormatString: "#0.##",
                                indexLabel: "{label}: {y} = #percent%",
                                dataPoints: JSON.parse(dataPoints)
                            }]
                    });
                    chart.render();
                } else {
                    var chart = new CanvasJS.Chart("chart", {
                        height: 550,
                        animationEnabled: true,
                        animationDuration: 1000,
                        theme: "light1", // "light1", "light2", "dark1", "dark2"
                        exportFileName: type, //Give any name accordingly
                        exportEnabled: true,
                        title: {
                            text: type
                        },
                        axisX: {
                            title: "Time"
                        },
                        axisY: {
                            title: getStaisticUnit()
                        },
                        data: [{
                                type: "column",
                                dataPoints: JSON.parse(dataPoints)
                            }]
                    });
                    chart.render();
                }
            }
        }
    });
}


function explodePie(e) {
    if (typeof (e.dataSeries.dataPoints[e.dataPointIndex].exploded) === "undefined" || !e.dataSeries.dataPoints[e.dataPointIndex].exploded) {
        e.dataSeries.dataPoints[e.dataPointIndex].exploded = true;
    } else {
        e.dataSeries.dataPoints[e.dataPointIndex].exploded = false;
    }
    e.chart.render();

}