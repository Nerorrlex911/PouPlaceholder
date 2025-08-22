function onPlaceholderRequest(params, entity, def) {
    print("Test Placeholder Script Registering");
    return "Test Placeholder: " + params.join(", ") + "default: " + def + " entity: " + entity.getCustomName().toString();
}

function onEnable() {
    print("Test Placeholder Script Enabled");
    PlaceholderTool.registerPlaceholder("testplaceholder","testplaceholder","zimablue","1.0","testplaceholder.js::onPlaceholderRequest");
}