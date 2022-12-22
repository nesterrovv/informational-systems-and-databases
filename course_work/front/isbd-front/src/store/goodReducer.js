const defaultState = {
    goods: []
}

export const goodReducer = (state = defaultState, action) => {
    switch (action.type) {
        case "ADD_GOOD":
            // return
        default:
            return state;
    }
}