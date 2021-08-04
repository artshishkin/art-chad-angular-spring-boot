import {Coach} from "./Coach";

export class SwimmingCoach implements Coach{

    getDailyWorkout(): string {
        return "Swim 2 km";
    }
}