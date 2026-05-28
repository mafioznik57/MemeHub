# Local development setup

## If you built this in Lovable (free plan)

**Lovable does not show Supabase API keys on the website.** Keys are injected automatically on Lovable’s hosted preview, but for **local** `npm run dev` you must copy them from Supabase (or from the browser — see below).

Your downloaded project is linked to Supabase project **`hsgcjknghqsjtfonqkbk`**  
URL: `https://hsgcjknghqsjtfonqkbk.supabase.co`

### Option A — Supabase Dashboard (recommended)

1. Sign up or log in at [supabase.com](https://supabase.com) (use the **same account** you used when Lovable asked you to “Connect Supabase”).
2. Open [your projects list](https://supabase.com/dashboard/projects).
3. Open the project whose **Reference ID** is `hsgcjknghqsjtfonqkbk` (name may be “FourWin” or similar).
4. Go to **Project Settings** (gear) → **API**.
5. Copy **Project URL** and **`anon` `public`** key (long `eyJ...` string).
6. Paste into `.env` (see Quick start step 2).

Direct link: [API settings for this project](https://supabase.com/dashboard/project/hsgcjknghqsjtfonqkbk/settings/api)

### Option B — Copy key from Lovable preview (no Supabase dashboard)

1. In Lovable, open your FourWin project and click **Preview** (or open `https://fourwin-team-up.lovable.app` if published).
2. Press **F12** → **Network** tab.
3. Refresh the page. Filter by `supabase`.
4. Click any request to `*.supabase.co`.
5. In **Request Headers**, copy the value of **`apikey`** (and confirm URL matches `hsgcjknghqsjtfonqkbk.supabase.co`).
6. Put that value in `.env` as `VITE_SUPABASE_PUBLISHABLE_KEY` and `SUPABASE_PUBLISHABLE_KEY`.

### Option C — No Supabase project in your account

If you never connected your own Supabase account (or only used Lovable Cloud with no dashboard access):

1. Create a **free** project at [supabase.com](https://supabase.com).
2. Copy its URL + **anon** key into `.env`.
3. Run migrations: `npx supabase link --project-ref YOUR_NEW_REF` then `npx supabase db push`  
   (or run SQL files from `supabase/migrations/` in the SQL Editor).

### Where things are in Lovable (not API keys)

| What you need | Where in Lovable |
|---------------|------------------|
| Email login / users | **Cloud** tab (`+` next to Preview) → **Users & Auth**, or Supabase Dashboard → Authentication |
| Enable Google | Supabase → **Authentication → Providers → Google** (Lovable UI does not enable this for local dev) |
| Connect Supabase | **Settings** (workspace) → **Connectors** / **Integrations** → Supabase |

---

## Quick start

1. **Install dependencies** (once):

   ```powershell
   npm install
   ```

2. **Configure Supabase** — copy `.env.example` to `.env` and set your **anon (public) key**:

   - [Supabase Dashboard → API](https://supabase.com/dashboard/project/hsgcjknghqsjtfonqkbk/settings/api) (project `hsgcjknghqsjtfonqkbk`)
   - Or Lovable: **Project → Cloud / Integrations → Supabase**

   Or use the CLI (after `npx supabase login`):

   ```powershell
   .\scripts\setup-supabase-env.ps1
   ```

3. **Apply database migrations** (if the database is empty):

   ```powershell
   npx supabase link --project-ref hsgcjknghqsjtfonqkbk
   npx supabase db push
   ```

4. **Run the dev server**:

   ```powershell
   npm run dev
   ```

   Open **http://localhost:8080/**

## Auth errors (login / register)

### "Invalid API key"

Your `.env` still has a placeholder, not the real **anon public** key from Supabase.

1. Open [API Settings](https://supabase.com/dashboard/project/hsgcjknghqsjtfonqkbk/settings/api) (or Lovable → Cloud → Supabase).
2. Copy the **`anon` `public`** key (starts with `eyJ...`).
3. Paste it into `.env` for both `VITE_SUPABASE_PUBLISHABLE_KEY` and `SUPABASE_PUBLISHABLE_KEY`.
4. Restart: stop the dev server, then `npm run dev`.

### Google: `Unsupported provider: provider is not enabled`

Google sign-in must be turned on in Supabase (the app cannot enable it from code alone):

1. [Authentication → Providers](https://supabase.com/dashboard/project/hsgcjknghqsjtfonqkbk/auth/providers) → enable **Google**.
2. Add your Google OAuth Client ID and Secret (from [Google Cloud Console](https://console.cloud.google.com/)).
3. Under [URL Configuration](https://supabase.com/dashboard/project/hsgcjknghqsjtfonqkbk/auth/url-configuration), add redirect URLs:
   - `http://localhost:8080/**`
   - `http://127.0.0.1:8080/**`
4. Set **Site URL** to `http://localhost:8080` for local testing.

Until Google is enabled, use **email + password** register/sign-in (after fixing the API key).

## Testing multiplayer on one PC

Open several browser tabs to the same room — each tab gets its own player (`sessionStorage` client id).

## Requirements

- Node.js 18+ (20+ recommended)
- Supabase project with migrations applied
- Optional: Docker Desktop — only if you want `npx supabase start` (local Supabase) instead of cloud
