import {combineReducers, createStore} from 'redux'
import {goodReducer} from "./goodReducer";
import {userReducer} from "./userReducer";

const rootReducer = combineReducers({
    goods: goodReducer,
    user: userReducer
})
export const store = createStore(rootReducer);
