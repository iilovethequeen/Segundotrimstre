# UD1 Odoo Final Review: Complete Walkthrough (Odoo 18 Community)

> **Important:** these eight exercises are performed by clicking through *your own* local Odoo database. I can't log into your instance, so I have not executed them there. This guide gives you every click, every value, the reason behind each step, and the exact check to run afterwards. I also prepared two ready-to-use CSV files for Exercise 02.
>
> Menu and field labels can differ slightly between installations (the worksheet says so too). Use the equivalent option you see.

## 0. Ground rules (apply to everything)

- Work in a **test database**. Make sure the apps **Contacts, CRM, Sales, Project and Calendar** are installed (Apps menu).
- Put **`[REP26]`** at the start of every new record name, so you can find it with one search and tell it apart from existing data.
- Use fake data only. Emails end in **`@example.test`**.
- **Never** confirm quotations, send emails/invitations, create invoices, or move inventory. Quotations stay in *Draft / Quotation* state.
- After each save, **close and reopen the record** to prove it persisted. Odoo autosaves when you leave a form, but the "Save" cloud icon is the safe way.

### Data you will create (cheat sheet)

| Ex. | Records |
|---|---|
| 01 | 4 companies, 3 people |
| 02 | 3 companies via CSV |
| 03 | 3 opportunities: 3,600 € / 900 € / 240 € |
| 04 | 3 service products, 2 quotations (330 € and 160 €) |
| 05 | 1 project, 6 tasks |
| 06 | 3 calendar events |
| 07 | Centro Cauce: company + person + opportunity + quotation + project (5 tasks) + event |
| 08 | Audit of everything above |

---

## Exercise 01 – Contacts: companies, people, no duplicates

**Concept:** in Odoo, a contact is a `res.partner`. A *company* and an *individual* are the same table; an individual points to a company through the **Company** field (parent). That link is what keeps people attached to companies.

**Steps**

1. **Contacts → New**. Choose the **Company** radio button (not Individual). Create these four, each with a different fake email:
   - `[REP26] Biblioteca Ronda` – `biblioteca.ronda@example.test`
   - `[REP26] Centro Horizonte` – `centro.horizonte@example.test`
   - `[REP26] Academia Mirador` – `academia.mirador@example.test`
   - `[REP26] Distribuciones Nube` – `distribuciones.nube@example.test`
2. In each company's **Internal Notes** tab, write whether it acts as **customer** or **supplier** (e.g. Biblioteca Ronda, Centro Horizonte, Academia Mirador: *customer*; Distribuciones Nube: *supplier*). The worksheet asks for a note on purpose, because Odoo derives customer/vendor rank automatically from sales and purchases.
3. Create three **Individuals**, and fill the **Company** field (or add them from the company's *Contacts & Addresses* tab → **Add**):
   - `[REP26] Laura Ronda` → Biblioteca Ronda, job position, `laura.ronda@example.test`
   - `[REP26] Sara Vega` → Centro Horizonte, job position, `sara.vega@example.test`
   - `[REP26] Mario Luna` → Distribuciones Nube, job position, `mario.luna@example.test`
4. Open **Centro Horizonte**, change the **Phone** (e.g. `+34 600 000 001`), save, **leave the record and reopen it**. Do **not** create a new card to fix it.
5. Search `REP26` (or part of a name such as `Hori`). Apply the **Companies** filter, then **Group By** an option available (Salesperson, Country, etc.). Remove the filter and the grouping and observe how the list changes (filtered/grouped vs. flat list).
6. Search `REP26` again and check each person's company.

**Check:** exactly **4 companies and 3 persons**, correctly linked (not 7 companies). The phone change persists.

---

## Exercise 02 – CSV: import, verify, export

**Concept:** importing is a controlled process: *export a sample → learn the field names → prepare the file → Test → Import once → verify → export only what is needed.*

**Files prepared for you:** `E02_importacion.csv` (3 companies, UTF-8) and `E02_importacion_defectuosa.csv` (same, but one row has an empty name).

`E02_importacion.csv`:

```
Name,Email,Company Type
[REP26] TecnoLuz,tecnoluz@example.test,Company
[REP26] Editorial Alba,editorialalba@example.test,Company
[REP26] Oficina Río,oficinario@example.test,Company
```

**Steps**

1. **Export a test contact:** in Contacts list select one contact (checkbox) → **Actions (gear) → Export**. Tick *"I want to update data (import-compatible export)"*, add **Name** and **Email**, and export. Open the result: this shows the exact column names Odoo uses (`Name`, `Email`, …). That's why my CSV uses those headers. The `Río` accent is there to test UTF-8.
2. **Open the import wizard:** Contacts → **Favorites (star) → Import records** (in some versions an **Import** button appears next to *New*). Upload `E02_importacion.csv`. Check the column mapping: *Name → Name*, *Email → Email*, *Company Type → Company Type*. Company Type is the field that marks a record as a **company**; if your version offers *"Is a Company"* instead, map to that. Click **Test** first (it must say it looks correct / no errors), then check the final type after import.
3. **Run the import once.** Search `REP26`: each of the three companies must appear **once**. If the list shows them as individuals, open each and switch the type to Company from the interface.
4. **Test the faulty file:** upload `E02_importacion_defectuosa.csv` and click **Test** only. Read the warning/error about the empty Name row. **Do not click Import** for that file.
5. **Export only the three imported companies:** select just those three rows → **Actions → Export** → fields **Name** and **Email** → CSV. Open it and compare with your original: header present, **3 data rows**, accents (`Río`) readable, emails all different.

**Check:** the exported CSV has **one header + three data rows**. Odoo may use `,` or `;` as separator depending on configuration; that is not penalised.

---

## Exercise 03 – CRM: opportunities, activities, funnel

**Concept:** an *opportunity* is a `crm.lead` with a customer, an expected revenue and a **stage**. *Activities* are scheduled to-dos (call, meeting, to-do); they are different from *notes* in the chatter.

Reuse **Centro Horizonte** and **Academia Mirador** from Ex. 01. Do not create them again.

**Steps**

1. **CRM → Pipeline → New** (or the "+" in a column):
   - `[REP26] Centro Horizonte - 6 portátiles`: Customer **Centro Horizonte**, Expected Revenue **3,600 €**.
   - `[REP26] Academia Mirador - 2 proyectores`: Customer **Academia Mirador**, Expected Revenue **900 €**.
2. Third opportunity: `[REP26] Centro Horizonte - mantenimiento`, Customer **Centro Horizonte**, Expected Revenue **240 €**. (Ex. 04 uses it.)
3. **Activities:**
   - On opportunity 1: *Schedule Activity* → type **Call**, a summary, a **future** due date. Then use **Log note** in the chatter to simulate the call outcome ("Fictitious call, customer asked for a quote"). **Only after that** click **Mark Done** on the call.
   - On opportunity 2: *Schedule Activity* → a **follow-up** (type To-Do/Email or any follow-up type), with summary and a future date. **Leave it pending.**
4. **Advance only opportunity 1** to a later stage (e.g. New → Qualified). Don't use Won/Lost. Add a **note** justifying the fictitious advance.
5. Search `REP26`, apply the **Open** (or opportunity) filter, group by **Stage**. Open each card and check the **customer** and **expected revenue**.

**Check:** three **open** opportunities with **3,600 €, 900 €, 240 €**; only one has changed stage.

---

## Exercise 04 – Sales: quotations connected to CRM

**Concept:** a quotation (`sale.order`) is created *from* the opportunity so that both are linked (the opportunity shows a **Quotations** smart button). The opportunity's *expected revenue* is its own field and **does not auto-update**.

**Steps**

1. **Sales → Products → Products → New.** Set **Product Type = Service** (check this field) and the price:
   - `[REP26] Puesta a punto de portátil` – **45 €**
   - `[REP26] Instalación de software` – **30 €**
   - `[REP26] Formación individual` – **35 €** and, if the *Units of Measure* setting is active, unit **Hours** (otherwise just keep price 35 and note it is per hour).
2. Open `[REP26] Centro Horizonte - mantenimiento` (E03) → **New Quotation**. Add lines: **4 × Puesta a punto** + **2 × Instalación**.
   - Check: 4 × 45 + 2 × 30 = **240 €** untaxed.
3. Edit the **same** quotation: change puesta a punto from **4 to 6** units. New subtotal: 6 × 45 + 2 × 30 = **330 €**. Save and **do not confirm**.
4. Create a **new opportunity** for **Academia Mirador** (e.g. `[REP26] Academia Mirador - servicios`) and from it a **second quotation**: **3 × Instalación de software + 2 × Formación individual** → 3 × 30 + 2 × 35 = **160 €**. Don't confirm.
   - *Note:* this creates a 4th open opportunity; the "three opportunities" check belonged to Ex. 03.
5. **Sales → Quotations**, search `REP26`: both appear. Reopen the maintenance opportunity and click the **Quotations** smart button. Its **Expected Revenue is still 240 €** (unless you edit it manually).

**Check:** **330 €** and **160 €** untaxed (look at *Untaxed Amount*; taxes may change the total). Both are *Quotation* state.

---

## Exercise 05 – Project: planning and tracking

**Concept:** a task has a **stage** (the kanban column) and a separate **status/state** (In Progress, Changes Requested, Approved, Done, Cancelled in Odoo 18). The worksheet calls the latter "Listo" ("Ready/Done"). They are different concepts.

**Steps**

1. **Project → New**: `[REP26] AulaTec - revisión de equipos`. In its kanban add stages **Pendiente**, **En curso**, **Finalizado** (or adapt existing ones: To Do / In Progress / Done).
2. Add six tasks, each with a concrete description:
   - Inventariar equipos · Revisar antivirus · Actualizar navegadores · Comprobar red · Redactar informe · Documentar incidencias
3. Give a **future deadline** to **three different tasks**, and **assign one** to yourself.
4. Drag **Inventariar equipos** and **Revisar antivirus** to **En curso**. Then drag only **Inventariar equipos** to **Finalizado** and set its **status to Done** (the green check). Observe: the stage is *where* it is on the board; the state is its *condition*.
5. Open **Actualizar navegadores**, change its deadline, log a **note** (chatter) justifying it, save, close and reopen.
6. View the board, use a filter (e.g. *My Tasks*), remove it, and count: **exactly six tasks**, no duplicates.

**Check:** **1 in Finalizado, 1 in En curso, 4 in Pendiente.**

---

## Exercise 06 – Calendar: events, changes, CRM activities

**Concept:** events are `calendar.event` records. Meetings *scheduled from a CRM activity* of type **Meeting** create a calendar event; other activity types (Call, To-Do) do **not**.

**Steps** (do not send invitations: keep only yourself as attendee, or untick any email notification)

1. **Calendar → New**: `[REP26] Reunión interna AulaTec`, **45 minutes**, on an upcoming weekday, description "Internal coordination."
2. Switch between **Week** and **Month** views and confirm it appears once.
3. Create two **30-minute** events at the **same time**: `[REP26] Revisión de material` and `[REP26] Preparar aula`. See them overlap (side by side in week view). Drag/edit the second one so it no longer overlaps.
4. Move the 45-minute meeting to another day (drag or edit). Open it and verify the duration is still **45 minutes**.
5. In `[REP26] Centro Horizonte - 6 portátiles` (E03) schedule an activity of type **Meeting** (or follow-up). Then look in Calendar: *some installations* show a linked event, others don't. Report what you actually see; don't assume.

**Check:** exactly **one** 45-minute internal meeting. Not every event is linked to CRM.

---

## Exercise 07 – Integrating case: Centro Cauce

**Concept:** the same customer name appears in five apps. Some records are **truly linked** (Many2one fields); others only **share a name**.

**Steps**

1. **Contacts:** company `[REP26] Centro Cauce` (fake email) and person `[REP26] Elena Ruiz` with **Company = Centro Cauce**, fake email.
2. **CRM:** opportunity `[REP26] Centro Cauce - 6 puestos y formación`, Customer **Centro Cauce**, Expected Revenue **490 €**. Schedule activity named **Definir alcance**, add an internal **note**, advance to a later stage **without** marking Won.
3. **Sales:** create service `[REP26] Preparación de puesto informático` at **70 €**. Reuse **Formación individual (35 €/h)** from Ex. 04 (don't duplicate it). From the opportunity click **New Quotation**: **6 × Preparación** + **2 × Formación** = 420 + 70 = **490 €** untaxed. Leave as **draft/unconfirmed**.
4. **Project:** `[REP26] Centro Cauce - aula de formación` with five tasks: Revisar puestos, Preparar equipos, Comprobar conectividad, Formar usuarios, Entregar informe. Spread them over **two or more stages** and give **deadlines to three**.
5. **Calendar:** `[REP26] Coordinación Centro Cauce`, **30 minutes**, no external attendees.
6. **Verify coherence** across apps (see table).

| Relationship | Real link? |
|---|---|
| Opportunity ↔ Customer | **Yes** (Customer field) |
| Opportunity ↔ Quotation | **Yes** (quote created from the opportunity; smart button) |
| Quotation ↔ Customer | **Yes** (same partner) |
| Project ↔ Quotation | **No** (only a shared name unless a service product auto-creates a project) |
| Project ↔ Customer | Only if you filled the project's *Customer* field |
| Calendar event ↔ Opportunity | **No**, unless created from an opportunity activity or linked manually |

**Check:** **1 company + 1 person + 1 opportunity + 1 quotation + 1 project with 5 tasks + 1 event.** The quotation is **not** a sales order.

---

## Exercise 08 – Data audit and final review

**Steps**

1. Search **`[REP26] Oficina Río`** (from Ex. 02). Add a fake phone (or fix an existing one), save, reopen: it must be the **same record** (no duplicate).
2. Export to CSV **only** *Biblioteca Ronda, Centro Horizonte and Academia Mirador* with **Name** and **Email**. Open the file: **3 data rows**, accents intact, no duplicates.
3. Verify the opportunity **Centro Horizonte - mantenimiento** belongs to **Centro Horizonte** and its quotation has **330 €** untaxed. Verify **Centro Cauce** and its **490 €** quotation.
4. Open the Centro Cauce project and its calendar event. Decide whether they are **directly linked** to the quotation or only **share a name** (per the table in Ex. 07).
5. Do **at least six checks**. Suggested list:

| # | Check | Expected |
|---|---|---|
| 1 | Search `REP26` in Contacts | 4 + 3 (Ex. 01) + 3 (Ex. 02) + Centro Cauce & Elena Ruiz = 12 records, no duplicates |
| 2 | Every person has the right company | Yes |
| 3 | Open opportunities in CRM | 3,600 / 900 / 240 / 490 (+ the extra Academia Mirador one from Ex. 04 if created) |
| 4 | Maintenance quote | 330 € untaxed, draft |
| 5 | Academia Mirador quote | 160 € untaxed, draft |
| 6 | Centro Cauce quote | 490 € untaxed, draft |
| 7 | Projects | AulaTec: 6 tasks (1/1/4); Cauce: 5 tasks |
| 8 | Calendar | One 45-min internal meeting, no overlaps, Cauce event 30 min |

   If everything is correct, **write that down**; don't invent problems. If you find an inconsistency (e.g. a duplicate or a wrong amount), fix it **in the existing record**.

**Check:** no duplicates from imports, and **no confirmed quotations** anywhere.

---

## Final self-review checklist

- [ ] The identifying prefix **`[REP26]`** is on all new records.
- [ ] Imported contacts were exported again **from Odoo** (not hand-edited CSV).
- [ ] Opportunities show the right customer, stage, revenue and activities.
- [ ] Quotations keep quantities, subtotals and **draft** status.
- [ ] Boards, events and changes are saved (reopen to confirm).
- [ ] CSVs imported/exported with readable accents (`Río`).

## Quick troubleshooting

| Problem | Fix |
|---|---|
| Imported companies appear as individuals | Map *Company Type* (or *Is a Company*) or switch each record to Company in the form |
| Accents look broken in the CSV | Save as **UTF-8**; in the import wizard set encoding to UTF-8 |
| Can't set unit "Hours" | Enable *Units of Measure* in Sales → Settings, or just keep the 35 € price |
| No "Quotations" smart button | The quote wasn't created from the opportunity; create it from the opportunity's *New Quotation* button |
| No *Done* status on tasks | Older versions use the kanban state (grey/red/green); use the green "ready" circle |