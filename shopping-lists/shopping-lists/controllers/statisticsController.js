import { renderFile } from "https://deno.land/x/eta@v1.12.3/mod.ts";
import * as statisticsService from "../services/statisticsService.js";

const responseDetails = {
  headers: { "Content-Type": "text/html;charset=UTF-8" },
};

const viewStatistics = async (_request) => {
  const data = {
    listCount: (await statisticsService.listCount()).rows[0].count,
    itemCount: (await statisticsService.itemCount()).rows[0].count,
  };
  return new Response(
    await renderFile("statistics.eta", data),
    responseDetails,
  );
};

export { viewStatistics };
