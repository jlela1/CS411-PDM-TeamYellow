document.addEventListener("DOMContentLoaded", function () {
    const capacitySlider = document.getElementById("capacity-slider");
    const capacityValue = document.getElementById("capacity-value");
    const vehiclesSlider = document.getElementById("vehicles-slider");
    const vehiclesValue = document.getElementById("vehicles-value");
    const parkTimeSlider = document.getElementById("park-time-slider");
    const parkTimeValue = document.getElementById("park-time-value");
    const durationSlider = document.getElementById("duration-slider");
    const durationValue = document.getElementById("duration-value");
    const garage1CapacitySlider = document.getElementById("garage1-capacity-slider");
    const garage1CapacityValue = document.getElementById("garage1-capacity-value");
    const garage2CapacitySlider = document.getElementById("garage2-capacity-slider");
    const garage2CapacityValue = document.getElementById("garage2-capacity-value");
    const startSimulationButton = document.getElementById("start-simulation");

   
    function animateSlider(slider, value, valueElement) {
        const stepDuration = 50;
        const totalSteps = 20;
        const stepValue = (value - slider.value) / totalSteps;
        let currentStep = 0;

        const interval = setInterval(function () {
            if (parseInt(slider.value) === value) {
                clearInterval(interval);
            } else {
                slider.value = Math.round(parseFloat(slider.value) + stepValue);
                valueElement.textContent = slider.value;
            }
        }, stepDuration);
    }

   
    capacitySlider.addEventListener("input", function () {
        capacityValue.textContent = capacitySlider.value;
    });

   
    vehiclesSlider.addEventListener("input", function () {
        vehiclesValue.textContent = vehiclesSlider.value;
    });

   
    parkTimeSlider.addEventListener("input", function () {
        parkTimeValue.textContent = parkTimeSlider.value;
    });

   
    durationSlider.addEventListener("input", function () {
        durationValue.textContent = durationSlider.value;
    });

   
    garage1CapacitySlider.addEventListener("input", function () {
        garage1CapacityValue.textContent = garage1CapacitySlider.value;
    });

   
    garage2CapacitySlider.addEventListener("input", function () {
        garage2CapacityValue.textContent = garage2CapacitySlider.value;
    });

   
    startSimulationButton.addEventListener("click", function () {
        const newCapacity = Math.floor(Math.random() * 100) + 1;
        const newVehicles = Math.floor(Math.random() * 60) + 1;
        const newParkTime = Math.floor(Math.random() * 60) + 1;
        const newDuration = Math.floor(Math.random() * 120) + 1;
        const newGarage1Capacity = Math.floor(Math.random() * 100) + 1;
        const newGarage2Capacity = Math.floor(Math.random() * 100) + 1;

        animateSlider(capacitySlider, newCapacity, capacityValue);
        animateSlider(vehiclesSlider, newVehicles, vehiclesValue);
        animateSlider(parkTimeSlider, newParkTime, parkTimeValue);
        animateSlider(durationSlider, newDuration, durationValue);
        animateSlider(garage1CapacitySlider, newGarage1Capacity, garage1CapacityValue);
        animateSlider(garage2CapacitySlider, newGarage2Capacity, garage2CapacityValue);

    });
});