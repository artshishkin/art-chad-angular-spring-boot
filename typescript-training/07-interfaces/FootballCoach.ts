import {Coach} from "./Coach";

export class FootballCoach implements Coach{

    getDailyWorkout(): string {
        return "Give me 10 goals";
    }
}