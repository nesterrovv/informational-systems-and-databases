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
        case "DELETE_GOOD": {
            let reducedGoods = state.goods.filter(good => good.id !== action.payload);
            return {...state, goods: reducedGoods};
        }
        case "UPDATE_GOOD": {
            let reducedGoods = state.goods.filter(good => good.id !== action.payload.id);
            reducedGoods.push(action.payload);
            return {...state, goods: reducedGoods};
        }
        default:
            return state;
    }
}