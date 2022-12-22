const defaultState = {
    nextId: 0,
    goods: []
}

export const goodReducer = (state = defaultState, action) => {
    switch (action.type) {
        case "ADD_GOOD":
            let newGoods = state.goods;
            let good = {
                id: state.nextId,
                length: 0,
                width: 0,
                height: 0,
                weight: 0,
                description: ''
            }
            newGoods.push(good);
            return {...state, goods: newGoods, nextId: state.nextId + 1};
        default:
            return state;
    }
}