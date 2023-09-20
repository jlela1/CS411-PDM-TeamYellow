
// Define vehicle object
// parkingIn: Time in minutes before the vehicle locates an available parking location
// parkingOut: Time in minutes before the vehicle exits the parking location
// parked: True means the vehicle is parked, false means it is waiting to park
// garageIndex: int 0-2 which represents the respective garages in garageData
let vehiclePrototype = {
    // Set by user defined parameters
    parkingIn: 0,
    parkingOut: 0,
    parked: false,
    garageIndex: null,
};

// Declare variables and get values from user input for simulation
const garage0Capacity = 300;
const garage1Capacity = 500;
const garage2Capacity = 250;
let avgVehiclesParkingPerMinute = 15;
let avgTimeToPark = 10;
let avgTimeParked = 60;

// Duration of simulation in minutes
// 420 == 7AM
const startTime = 420;
let currentTime = startTime;
const simulationDuration = 720;

// Array to hold vehicles waiting to park or leave a parking location
let vehicleQueue = [];

// Simulated data for garage capacities and occupancies
//let garageData = [
//    { name: "Garage 1: 43rd & Elkhorn", capacity: garage1Capacity, occupancy: Math.floor(Math.random() * 101) },
//    { name: "Garage 2: Football Stadium", capacity: garage2Capacity, occupancy: Math.floor(Math.random() * 101) },
//    { name: "Garage 3: 43rd & Bluestone", capacity: garage3Capacity, occupancy: Math.floor(Math.random() * 101) },
//];

let garageData = [
    { name: "Garage 1: 43rd & Elkhorn", capacity: garage0Capacity, occupancy: 0 },
    { name: "Garage 2: Football Stadium", capacity: garage1Capacity, occupancy: 0 },
    { name: "Garage 3: 43rd & Bluestone", capacity: garage2Capacity, occupancy: 0 },
];

const garageElements = document.querySelectorAll(".garage");

// Function to assign a garage to a vehicle that is ready to park
function assignGarage(vehiclePrototype) {

    // Check if all garages are full
    if (
        garageData.every(garage => garage.occupancy >= garage.capacity)
    ) {
        // All garages are full, return without doing anything
        return;
    }

    // Generate an index 0-2 to access a random garage
    // Simulates an equal probability that each garage is the closest to the vehicle
    const randGarageIndex = Math.floor(Math.random() * 3);

    const randomGarage = garageData[randGarageIndex];

    // Check if the occupancy is greater than 90% of the capacity
    if (randomGarage.occupancy >= 0.9 * randomGarage.capacity) {

        // Check if all garages are above 90% capacity before trying to reassign
        // If so, assign to the selected garage if it is below 100% capacity
        if (garageData[0].occupancy >= 0.9 * garageData[0].capacity && garageData[1].occupancy >= 0.9 * garageData[1].capacity && garageData[2].occupancy >= 0.9 * garageData[2].capacity) {

            if (randomGarage.capacity > randomGarage.occupancy) {

                // Increment random garage occupancy as there is space for the vehicle
                randomGarage.occupancy += 1;
                // Set the vehicle's parked property to true so that parkingOut can begin to decrement
                vehiclePrototype.parked = true;
                // Record which garage the vehicle will be parked in for later removal
                vehiclePrototype.garageIndex = randGarageIndex;

            } else {

                // Garage is full, try assigning the vehicle to another garage by calling the function recursively
                assignGarage(vehiclePrototype);

            }

        }

        // Try assigning the vehicle to another garage by calling the function recursively
        assignGarage(vehiclePrototype);

    } else {

        // Increment random garage occupancy as there is space for the vehicle
        randomGarage.occupancy += 1;
        // Set the vehicle's parked property to true so that parkingOut can begin to decrement
        vehiclePrototype.parked = true;
        // Record which garage the vehicle will be parked in for later removal
        vehiclePrototype.garageIndex = randGarageIndex;

    }

}

function minutesToClockTime(minutes) {

  const hours = Math.floor(minutes / 60);
  const remainingMinutes = minutes % 60;
  const ampm = hours >= 12 ? "PM" : "AM";
  const formattedHours = hours % 12 || 12;
  const formattedMinutes = remainingMinutes < 10 ? "0" + remainingMinutes : remainingMinutes;

  return `${formattedHours}:${formattedMinutes} ${ampm}`;

}

// Function to update garage information
// Each call represents one minute of simulated time
function updateGarageInfo() {

    // Loop through vehicleQueue and decrement parkingIn or parkingOut by 1 minute
    // Dependent upon whether the vehicle is already parked
    for (let i = 0; i < vehicleQueue.length; i++) {

        if (vehicleQueue[i].parked == false) {

            vehicleQueue[i].parkingIn -= 1;

            // If the vehicle's waiting to park time has expired set parked = true
            // Assign it a garage
            if (vehicleQueue[i].parkingIn <= 0) {

                assignGarage(vehicleQueue[i]);

            }

        } else {

            vehicleQueue[i].parkingOut -= 1;

            // If the vehicle's parking time has expired remove it from the queue
            if (vehicleQueue[i].parkingOut <= 0) {

                //Decrement the occupancy of the garage containing the vehicle
                garageData[vehicleQueue[i].garageIndex].occupancy -= 1;
                vehicleQueue.splice(i, 1);

            }

        }

    }

    // Generate a bounded random number of vehicles based on user input avgVehiclesParkingPerMinute
    const randomOffset = Math.random() * 10;
    const vehiclesParkingThisMinute = avgVehiclesParkingPerMinute - 5 + randomOffset;

    // Generate number of vehicle objects == vehiclesParkingThisMinute and store them in a list vehicleQueue
    for (let i = 0; i < vehiclesParkingThisMinute; i++) {

        // Create a new vehicle object
        let newVehicle = Object.create(vehiclePrototype)
        // Generate a random bounded timeToPark number +/- 5 minutes from user input that is at least 1
        const ttpRandomOffset = Math.random() * 10;
        let timeToPark = avgTimeToPark - 5 + randomOffset;
        timeToPark = Math.max(1, timeToPark);
        // Generate a random bounded timeParked number +/- 15 minutes from user input that is at least 1
        const tpRandomOffset = Math.random() * 30;
        let timeParked = avgTimeParked - 15 + randomOffset;
        timeParked = Math.max(1, timeParked);
        //Assign these values to the newVehicle object
        newVehicle.parkingIn = timeToPark;
        newVehicle.parkingOut = timeParked;

        //Add the vehicle to the waiting array
        vehicleQueue.push(newVehicle);

    }

    garageElements.forEach((garageElement, index) => {
        const garage = garageData[index];
        const capacityValue = garageElement.querySelector(".capacity-value");
        const occupancyValue = garageElement.querySelector(".occupancy-value");
        const occupancyFillElement = garageElement.querySelector(".occupancy");

        capacityValue.textContent = garage.capacity;

        const occupancyPercentage = (garage.occupancy / garage.capacity) * 100;
        const roundedPercentage = Math.min(100, Math.round(occupancyPercentage));
        occupancyValue.textContent = garage.occupancy + "/" + garage.capacity + " - " + roundedPercentage + "%";

        // Update occupancy fill bar (as a percentage)
        occupancyFillElement.style.width = occupancyPercentage + "%";
    });

    //Update the time
    const currentTimeElement = document.getElementById("current-time");
    let clockTime = minutesToClockTime(currentTime);
    currentTimeElement.textContent = "Current Time: " + clockTime;
}

// Function to run the simulation for a specific duration
function runSimulation() {

  if (currentTime <= (startTime + simulationDuration)) {

    // Call updateGarageInfo function for each minute
    updateGarageInfo();
    currentTime++;

    // Call the runSimulation function recursively after a delay (e.g., 1000ms for 1 minute)
    setTimeout(runSimulation, 400); // Use setTimeout instead of setInterval

  }

}

runSimulation();