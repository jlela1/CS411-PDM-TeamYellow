
let vehiclesInt = 1;
let parkTimeInt = 1;
let durationInt = 1;
let capacityInt = 1;
let garage1CapacityInt = 1;
let garage2CapacityInt = 1;

document.addEventListener("DOMContentLoaded", function () {
const capacitySlider = document.getElementById("capacity-slider");
let capacityValue = document.getElementById("capacity-value");
const vehiclesSlider = document.getElementById("vehicles-slider");
let vehiclesValue = document.getElementById("vehicles-value");
const parkTimeSlider = document.getElementById("park-time-slider");
let parkTimeValue = document.getElementById("park-time-value");
const durationSlider = document.getElementById("duration-slider");
let durationValue = document.getElementById("duration-value");
const garage1CapacitySlider = document.getElementById("garage1-capacity-slider");
let garage1CapacityValue = document.getElementById("garage1-capacity-value");
const garage2CapacitySlider = document.getElementById("garage2-capacity-slider");
let garage2CapacityValue = document.getElementById("garage2-capacity-value");
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

   
    function handleCapacityChange() {
        capacityValue.textContent = capacitySlider.value;
    }

    capacitySlider.addEventListener("input", handleCapacityChange);
   
    function handleVehiclesChange() {
        vehiclesValue.textContent = vehiclesSlider.value;
    }

    vehiclesSlider.addEventListener("input", handleVehiclesChange);
   
    function handleParkTimeChange() {
        parkTimeValue.textContent = parkTimeSlider.value;
    }

    parkTimeSlider.addEventListener("input", handleParkTimeChange);

   
    function handleDurationChange() {
        durationValue.textContent = durationSlider.value;
    }

    durationSlider.addEventListener("input", handleDurationChange)
   
    function handleGarage1CapacityChange() {
        garage1CapacityValue.textContent = garage1CapacitySlider.value;
    }

    garage1CapacitySlider.addEventListener("input", handleGarage1CapacityChange);

    function handleGarage2CapacityChange() {
        garage2CapacityValue.textContent = garage2CapacitySlider.value;
    }

    garage2CapacitySlider.addEventListener("input", handleGarage2CapacityChange);

   
    startSimulationButton.addEventListener("click", function () {

        capacitySlider.removeEventListener("input", handleCapacityChange);
        garage1CapacitySlider.removeEventListener("input", handleGarage1CapacityChange);
        garage2CapacitySlider.removeEventListener("input", handleGarage2CapacityChange);
        vehiclesSlider.removeEventListener("input", handleVehiclesChange);
        parkTimeSlider.removeEventListener("input", handleParkTimeChange);
        durationSlider.removeEventListener("input", handleDurationChange);

        window.location.replace("../simulation/simulation.html");

        vehiclesInt = vehiclesSlider.value;
        parkTimeInt = parkTimeSlider.value;
        durationInt = durationSlider.value;
        capacityInt = capacitySlider.value;
        garage1CapacityInt = garage1CapacitySlider.value;
        garage2CapacityInt = garage2CapacitySlider.value;

        //export {vehiclesInt, parkTimeInt, durationInt, capacityInt, garage1CapacityInt, garage2CapacityInt};*/

    });
});