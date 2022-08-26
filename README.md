# Agenda de Exames
|AGENDAMENTO|APLICADOR|EXAME|PACIENTE
|---|---|
|**GET**|**GET**|**GET**|**GET**|
|/agendamento|/aplicador|
|/agendamento/lista|/aplicador/lista|
|/agendamento/id|/aplicador/id|
|/agendamento/horas/id/data|/aplicador/disponiveis/hora/data/especialidade|
|/agendamento/agendadata/data|**POST**|
|/agendamento/retiradadata/data|aplicador|
|**POST**|**PUT**|
|/agendamento|/aplicador/id|
|/agendamento/objeto|**PUT**|
|**PUT**|/aplicador/id|
|/agendamento/id|**DELETE**|
|**DELETE**|/aplicador/id|
|/agendamento/id|

#### GET
/aplicador<br/>
/aplicador/lista<br/>
/aplicador/id<br/>
/aplicador/disponiveis/hora/data/especialidade

#### POST
/aplicador

#### PUT
/aplicador/id

#### DELETE
/aplicador/id

### EXAME
#### GET
/exame<br/>
/exame/lista<br/>
/exame/id

#### POST
/exame

#### PUT
/exame/id

#### DELETE
/exame/id

### PACIENTE
#### GET
/paciente<br/>
/paciente/lista<br/>
/paciente/id

#### POST
/paciente

#### PUT
/paciente/id

#### DELETE
/paciente/id