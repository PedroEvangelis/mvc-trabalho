# GLOBAL_BEHAVIORS — Clínica Veterinária

## Exclusão em cascata (Tutor → Animal → Consulta)
- Ao excluir um tutor, todos os seus animais e as consultas desses animais são excluídos automaticamente.
- Justificativa: decisão do usuário durante CP4.

## Bloqueio de exclusão (Animal com consultas)
- Ao tentar excluir um animal que possui consultas registradas, a operação é bloqueada e uma mensagem de erro é exibida.
- Justificativa: decisão do usuário durante CP4.

## Valor mínimo da consulta
- O valor da consulta pode ser R$ 0,00 (consulta gratuita/cortesia).
- Valores negativos são rejeitados.
