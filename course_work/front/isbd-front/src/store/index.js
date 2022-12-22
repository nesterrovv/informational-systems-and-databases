import {combineReducers, createStore} from 'redux'
import {goodReducer} from "./goodReducer";

const rootReducer = combineReducers({
    goods: goodReducer
})
export const store = createStore(rootReducer);
