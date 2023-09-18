// Function to update garage information
function updateGarageInfo() {
console.log("JavaScript is executed!");

    // Simulated data for garage capacities and occupancies
    const garageData = [
        { name: "Garage 1: 43rd & Elkhorn", capacity: 100, occupancy: Math.floor(Math.random() * 101) },
        { name: "Garage 2: Football Stadium", capacity: 150, occupancy: Math.floor(Math.random() * 101) },
        { name: "Garage 3: 43rd & Bluestone", capacity: 80, occupancy: Math.floor(Math.random() * 101) },
];
     const garageElements = document.querySelectorAll(".garage");

        garageElements.forEach((garageElement, index) => {
            const garage = garageData[index];
            const capacityValue = garageElement.querySelector(".capacity-value");
            const occupancyValue = garageElement.querySelector(".occupancy-value");
            const occupancyFillElement = garageElement.querySelector(".occupancy");

            capacityValue.textContent = garage.capacity;
            occupancyValue.textContent = garage.occupancy + "%";

            // Update occupancy fill bar (as a percentage)
            const occupancyPercentage = (garage.occupancy / garage.capacity) * 100;
            occupancyFillElement.style.width = occupancyPercentage + "%";
        });
    }

// Update garage information every 5 seconds
setInterval(updateGarageInfo, 15000);


updateGarageInfo();