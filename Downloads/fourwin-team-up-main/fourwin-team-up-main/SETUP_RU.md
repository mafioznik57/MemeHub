# Локальный запуск FourWin (если проекта нет в Supabase)

## Почему вы не видите `hsgcjknghqsjtfonqkbk`

База данных создана **внутри Lovable**, а не в вашем личном аккаунте Supabase (GitHub).  
Поэтому на [supabase.com](https://supabase.com) пусто — это нормально.

Для локального `npm run dev` нужен **свой** бесплатный проект Supabase.

---

## Шаг 1 — Создать свой проект Supabase

1. Зайдите на [supabase.com](https://supabase.com) (через GitHub — как сейчас).
2. **New organization** (если просит) → имя любое.
3. **New project** → имя `fourwin-local`, пароль БД сохраните, регион любой.
4. Подождите 1–2 минуты, пока проект создастся.

---

## Шаг 2 — Скопировать ключи

1. В проекте: **Project Settings** (шестерёнка слева внизу) → **API**.
2. Скопируйте:
   - **Project URL** (например `https://abcdefgh.supabase.co`)
   - **anon public** (длинная строка `eyJ...`)

3. Откройте файл `.env` в папке проекта и вставьте:

```env
VITE_SUPABASE_URL=https://ВАШ-ID.supabase.co
VITE_SUPABASE_PUBLISHABLE_KEY=eyJ...ваш anon ключ...
SUPABASE_URL=https://ВАШ-ID.supabase.co
SUPABASE_PUBLISHABLE_KEY=eyJ...тот же anon ключ...
```

(`ВАШ-ID` — часть URL из шага 2, не `hsgcjknghqsjtfonqkbk`.)

---

## Шаг 3 — Создать таблицы (миграции)

1. В Supabase: **SQL Editor** → **New query**.
2. По очереди откройте файлы из папки `supabase/migrations/` **в порядке имени** (от старых к новым) и выполните **Run** для каждого:

   - `20260527171831_...sql`
   - `20260528060911_...sql`
   - `20260528071826_...sql`
   - `20260528081424_...sql`
   - `20260528081438_...sql`
   - `20260528081454_...sql`

---

## Шаг 4 — Настроить вход (auth)

**Email / пароль**

1. **Authentication** → **Providers** → **Email** — включён.
2. Для теста: **Authentication** → **Providers** → **Email** → отключите **Confirm email** (подтверждение почты).

**Google** (если нужен)

1. **Authentication** → **Providers** → **Google** — включить + Client ID/Secret из Google Cloud.
2. **Authentication** → **URL Configuration**:
   - Site URL: `http://localhost:8080`
   - Redirect URLs: `http://localhost:8080/**`

---

## Шаг 5 — Запуск

```powershell
cd "путь\к\fourwin-team-up-main"
npm install
npm run dev
```

Откройте http://localhost:8080

---

## Альтернатива: ключ из Lovable (без нового проекта)

Если проект в Lovable ещё открывается:

1. **Preview** в Lovable → **F12** → **Network** → обновить страницу.
2. Фильтр `supabase` → любой запрос → заголовок **apikey**.
3. Вставить в `.env` (URL из того же запроса).

Работает только пока Lovable даёт доступ к **их** базе; для долгой локальной разработки лучше **свой** проект (шаги выше).
