const modes = {
    "GreenTea": {
        title: "Green tea",
        description: "TODO",
        icon: "fas fa-hourglass-half",
        color: (component) => `${component}-green-500`,
    },
    "YellowTea": {
        title: "Yellow tea",
        description: "TODO",
        icon: "fas fa-sort-amount-up",
        color: (component) => `${component}-yellow-300`,
    },
    "RedTea": {
        title: "Red tea",
        description: "TODO",
        icon: "fas fa-ruler",
        color: (component) => `${component}-red-500`
    },
}

const aliases = {
    "green": "GreenTea",
    "yellow": "YellowTea",
    "red": "RedTea",
}

const optionally = (object, composition) =>
    object !== null && typeof object !== "undefined"
        ? composition(object)
        : object

export const alias = (mode) => aliases[mode]
export const color = (mode, component) => optionally(modes[mode], o => o.color(component)) || `${component}-gray-500`
export const icon  = (mode) => optionally(modes[mode], o => o.icon) || "fas fa-coffee"
export const title = (mode) => optionally(modes[mode], o => o.title) || "Untitled game mode"