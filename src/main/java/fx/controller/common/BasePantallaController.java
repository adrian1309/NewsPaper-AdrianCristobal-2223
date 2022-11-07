package fx.controller.common;

import fx.controller.FXMainController;

public class BasePantallaController {

    private FXMainController principalController;

    public FXMainController getPrincipalController() {
        return principalController;
    }

    public void setPrincipalController(FXMainController principalController) {
        this.principalController = principalController;
    }

    public void principalCargado()
    {

    }
}
