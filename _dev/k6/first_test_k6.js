import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 2,        // 2 виртуальных пользователя
    duration: '10s' // 10 секунд
};

export default function () {
    let payload_for_login = JSON.stringify({
        login: "max",
        password: "pass"
    });

    let params_for_login = {
        headers: {
            "Content-Type": "application/json"
        }
    }

    let res_login = http.post(
        'http://localhost:9090/api/auth/login',
        payload_for_login,
        params_for_login
    );

    let body_from_login = JSON.parse(res_login.body);
    let token = body_from_login.response;

    check(
        res_login, 
        {
            'status 200': (r) => r.status === 200
        }
    );

    sleep(1);

    let auth_param = {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }

    let res_event_all = http.get(
        'http://localhost:9090/api/event/all',
        auth_param
    );

    check(
        res_event_all,
        {
            'status 200': (r) => r.status === 200
        }
    )
}