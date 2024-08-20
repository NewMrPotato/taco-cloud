package tacos.Web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import lombok.extern.slf4j.Slf4j;
import tacos.TacoCook.TacoOrder;

import javax.validation.Valid;

// Контроллер для обработки полученного запроса POST.
// Взаитмодействие со страницей по пути /orders/current. #нефакт #незаконченно
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping   // Valid требует выполнить проверку TacoOrder по аннотациям с проверками в самом классе.
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus){
        // Проверка на наличие ошибок при создания пользователем заказа тако.
        // Аннотации с проверками в классе tacos.TacoCook.TacoOrder.
        if (errors.hasErrors()) {
            return "orderForm";
        }

        // Выводим информацию о заказе и тако.
        log.info("Order submitted: {}", order);
        // Гарантия очистки сеанса, в который был добавлен тако.
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
